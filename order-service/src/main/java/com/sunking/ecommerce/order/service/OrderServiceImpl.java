package com.sunking.ecommerce.order.service;

import com.sunking.ecommerce.order.clients.InventoryClient;
import com.sunking.ecommerce.order.dto.OrderDto;
import com.sunking.ecommerce.order.dto.request.OrderRequestDto;
import com.sunking.ecommerce.order.entity.OrderEntity;
import com.sunking.ecommerce.order.enums.OrderStatus;
import com.sunking.ecommerce.order.expception.OrderNotFoundException;
import com.sunking.ecommerce.order.repository.OrderRepository;
import com.sunking.ecommerce.order.utils.JwtUtil;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Override
    @RateLimiter(name = "default")
    @CircuitBreaker(name = "default")
    public OrderDto placeOrder(String token, OrderRequestDto orderRequestDto) {
        Long userId = Long.parseLong(jwtUtil.getSubject(token));
        inventoryClient.updateProductCount(orderRequestDto.getProductId(), -orderRequestDto.getQty());
        OrderEntity order = new OrderEntity();
        order.setUserId(userId);
        order.setProductId(orderRequestDto.getProductId());
        order.setQty(orderRequestDto.getQty());
        order.setStatus(OrderStatus.PLACED);
        order = orderRepository.saveAndFlush(order);

        /*
         * Publish to RabbitMQ for email notification
         */

        return new OrderDto(order);
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        order.setStatus(orderStatus);
        orderRepository.save(order);

        /*
         * Publish to RabbitMQ for email notification
         */

        return new OrderDto(order);
    }

}

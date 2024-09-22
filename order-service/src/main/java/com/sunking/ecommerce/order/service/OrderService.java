package com.sunking.ecommerce.order.service;

import com.sunking.ecommerce.order.dto.OrderDto;
import com.sunking.ecommerce.order.dto.request.OrderRequestDto;
import com.sunking.ecommerce.order.enums.OrderStatus;

public interface OrderService {

    OrderDto placeOrder(String token, OrderRequestDto orderRequestDto);

    OrderDto updateOrderStatus(Long orderId, OrderStatus orderStatus);

}

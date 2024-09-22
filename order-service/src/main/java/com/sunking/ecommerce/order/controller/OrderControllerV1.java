package com.sunking.ecommerce.order.controller;

import com.sunking.ecommerce.order.dto.request.OrderRequestDto;
import com.sunking.ecommerce.order.dto.response.ApiResponse;
import com.sunking.ecommerce.order.enums.OrderStatus;
import com.sunking.ecommerce.order.expception.BadRequestException;
import com.sunking.ecommerce.order.expception.OrderNotFoundException;
import com.sunking.ecommerce.order.expception.OutOfStockException;
import com.sunking.ecommerce.order.expception.ProductNotFoundException;
import com.sunking.ecommerce.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderControllerV1 {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse> placeOrder(@RequestBody OrderRequestDto orderRequestDto, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Order Placed", orderService.placeOrder(token, orderRequestDto)));
    }

    @PutMapping("/updateOrderStatus/{orderId}")
    public ResponseEntity<ApiResponse> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus orderStatus) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Order status updated",
                        orderService.updateOrderStatus(orderId, orderStatus)));
    }

    @ExceptionHandler({BadRequestException.class, OrderNotFoundException.class, ProductNotFoundException.class, OutOfStockException.class})
    public ResponseEntity<ApiResponse> handleCustomException(OutOfStockException ex) {
        return new ResponseEntity<>(
                new ApiResponse(false, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

}

package com.sunking.ecommerce.order.expception;

public class OrderNotFoundException extends BadRequestException {

    public OrderNotFoundException(String message) {
        super(message);
    }

}

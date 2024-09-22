package com.sunking.ecommerce.order.expception;

public class OutOfStockException extends BadRequestException {

    public OutOfStockException(String message) {
        super(message);
    }

}

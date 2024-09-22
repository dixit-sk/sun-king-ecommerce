package com.sunking.ecommerce.inventory.expception;

public class OutOfStockException extends BadRequestException {

    public OutOfStockException(String message) {
        super(message);
    }

}

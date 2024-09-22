package com.sunking.ecommerce.order.expception;

public class ProductNotFoundException extends BadRequestException {

    public ProductNotFoundException(String message) {
        super(message);
    }

}

package com.sunking.ecommerce.inventory.expception;

public class ProductNotFoundException extends BadRequestException {

    public ProductNotFoundException(String message) {
        super(message);
    }

}

package com.sunking.ecommerce.order.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Long price;
    private Long count;
}

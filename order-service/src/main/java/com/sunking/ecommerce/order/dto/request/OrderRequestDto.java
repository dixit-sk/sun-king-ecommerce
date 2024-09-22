package com.sunking.ecommerce.order.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDto {

    private Long productId;
    private Long qty;

}

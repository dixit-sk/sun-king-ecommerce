package com.sunking.ecommerce.order.dto;

import com.sunking.ecommerce.order.entity.OrderEntity;
import com.sunking.ecommerce.order.enums.OrderStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private Long productId;
    private Long qty;
    private OrderStatus status;

    public OrderDto(OrderEntity entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.productId = entity.getProductId();
        this.qty = entity.getQty();
        this.status = entity.getStatus();
    }
}

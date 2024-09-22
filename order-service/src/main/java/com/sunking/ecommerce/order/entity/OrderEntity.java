package com.sunking.ecommerce.order.entity;

import com.sunking.ecommerce.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long userId;

    @NonNull
    private Long productId;

    @NonNull
    private Long qty;

    @NonNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}


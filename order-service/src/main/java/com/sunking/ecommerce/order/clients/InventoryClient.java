package com.sunking.ecommerce.order.clients;

import com.sunking.ecommerce.order.dto.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PutMapping("/api/v1/inventory/products/updateCount/{productId}")
    ApiResponse updateProductCount(@PathVariable Long productId, @RequestParam Long increment);

}



package com.sunking.ecommerce.inventory.controller;

import com.sunking.ecommerce.inventory.dto.ProductDto;
import com.sunking.ecommerce.inventory.dto.response.ApiResponse;
import com.sunking.ecommerce.inventory.expception.BadRequestException;
import com.sunking.ecommerce.inventory.expception.OutOfStockException;
import com.sunking.ecommerce.inventory.expception.ProductNotFoundException;
import com.sunking.ecommerce.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory/products")
public class ProductControllerV1 {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse> getProducts() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Products fetched", productService.getProducts()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Product added", productService.addProduct(productDto)));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Product fetched", productService.getProduct(productId)));
    }

    @PutMapping("/updateCount/{productId}")
    public ResponseEntity<ApiResponse> updateProductCount(@PathVariable Long productId, @RequestParam int increment) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Product count updated",
                        productService.updateProductCount(productId, increment)));
    }


    @ExceptionHandler({BadRequestException.class, ProductNotFoundException.class, OutOfStockException.class})
    public ResponseEntity<ApiResponse> handleBadRequestException(OutOfStockException ex) {
        return new ResponseEntity<>(
                new ApiResponse(false, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

}

package com.sunking.ecommerce.inventory.service;

import com.sunking.ecommerce.inventory.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    ProductDto getProduct(Long productId);

    List<ProductDto> getProducts();

    ProductDto updateProductCount(Long productId, int increment);

}

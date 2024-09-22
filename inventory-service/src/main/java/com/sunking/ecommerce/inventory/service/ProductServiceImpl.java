package com.sunking.ecommerce.inventory.service;

import com.sunking.ecommerce.inventory.dto.ProductDto;
import com.sunking.ecommerce.inventory.entity.ProductEntity;
import com.sunking.ecommerce.inventory.expception.OutOfStockException;
import com.sunking.ecommerce.inventory.expception.ProductNotFoundException;
import com.sunking.ecommerce.inventory.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .count(productDto.getCount())
                .build();
        productEntity = productRepository.saveAndFlush(productEntity);
        return new ProductDto(productEntity);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return new ProductDto(product);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream().map(productEntity -> new ProductDto(productEntity)).toList();
    }

    @Override
    @Transactional
    public ProductDto updateProductCount(Long productId, int increment) {
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        synchronized (product) {
            Long newCount = product.getCount() + increment;
            // Check for insufficient stock
            if (newCount < 0)
                throw new OutOfStockException("Insufficient stock for product: " + product.getName());
            product.setCount(newCount);
            productRepository.save(product);
            return new ProductDto(product);
        }
    }
}

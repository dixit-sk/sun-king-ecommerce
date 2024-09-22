package com.sunking.ecommerce.inventory.config;

import com.sunking.ecommerce.inventory.dto.ProductDto;
import com.sunking.ecommerce.inventory.repository.ProductRepository;
import com.sunking.ecommerce.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Override
    public void run(String... args) throws Exception {
        // Check if the database is empty before adding users
        if (productRepository.count() == 0) {
            productService.addProduct(ProductDto.builder().name("Product 1").price(100L).count(10L).build());
            productService.addProduct(ProductDto.builder().name("Product 2").price(200L).count(20L).build());
            productService.addProduct(ProductDto.builder().name("Product 3").price(300L).count(30L).build());
        }
    }
}

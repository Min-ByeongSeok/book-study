package com.springboot.test.data.repository;

import com.springboot.test.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ProductRepositoryTestByH2 {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveTest() {
        Product product = Product.builder()
                .name("펜")
                .price(1000)
                .stock(1000)
                .build();

        Product savedProduct = productRepository.save(product);

        assertEquals(product.getNumber(), savedProduct.getNumber());
    }

    @Test
    void selectTest() {
        Product product = Product.builder()
                .name("펜")
                .price(1000)
                .stock(1000)
                .build();

        Product savedProduct = productRepository.saveAndFlush(product);

        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        assertEquals(product.getNumber(), foundProduct.getNumber());
    }
}
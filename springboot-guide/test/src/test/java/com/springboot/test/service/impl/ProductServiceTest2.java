package com.springboot.test.service.impl;

import com.springboot.test.data.repository.ProductRepository;
import com.springboot.test.service.ProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Import(ProductServiceImpl.class)
class ProductServiceTest2 {

    @MockBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

}
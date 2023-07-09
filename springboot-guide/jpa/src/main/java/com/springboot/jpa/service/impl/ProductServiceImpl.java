package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        ProductResponseDto productResponseDto =
                ProductResponseDto.builder()
                        .number(product.getNumber())
                        .name(product.getName())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .build();

        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {

        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        Product saveProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .number(saveProduct.getNumber())
                .name(saveProduct.getName())
                .stock(saveProduct.getStock())
                .price(saveProduct.getPrice())
                .build();

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changedProduct = productDAO.updateProductName(number, name);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .name(changedProduct.getName())
                .number(changedProduct.getNumber())
                .price(changedProduct.getPrice())
                .stock(changedProduct.getStock())
                .build();

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}

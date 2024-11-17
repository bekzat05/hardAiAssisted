package com.aitbekov.hard.services;

import com.aitbekov.hard.models.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();

    List<Product> getProductsByCategory(String category);

    Product getProductById(Long id);

    void updateStock(Long productId, int quantity);
}

package com.insert.company.name.productsbackend.services;

import com.insert.company.name.productsbackend.clients.ProductClient;
import com.insert.company.name.productsbackend.models.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Callum Cooper
 */
@Service
public class ProductService {

    @Autowired
    ProductClient productClient;

    public Product getProduct(String productId) {
        return productClient.getProduct(productId);
    }

    public List<Product> getProducts() {
        return productClient.getProducts();
    }
}

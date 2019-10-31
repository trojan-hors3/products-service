package com.insert.company.name.productsbackend.models.entities;

import lombok.Getter;

import java.util.List;

/**
 * @author Callum Cooper
 */
@Getter
public class Package {
    private String id;
    private String name;
    private String description;
    private List<Product> products;
    private Double price;

    public Package(String id, String name, String description, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
        this.price = products.stream().mapToDouble(Product::getPrice).sum();
    }
}
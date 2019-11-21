package com.insert.company.name.productsbackend.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author Callum Cooper
 */
@Getter
public class Product {
    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    private int price;

    @JsonProperty(value = "usdPrice")
    public void  setPrice(int price) {
        this.price = price;
    }

    @JsonProperty(value = "price")
    public double getPrice() {
        return this.price;
    }

    // Default constructor
    public Product() {}

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

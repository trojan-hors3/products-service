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

    @JsonProperty(value = "usdPrice")
    private int price;

    public Product() {}

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

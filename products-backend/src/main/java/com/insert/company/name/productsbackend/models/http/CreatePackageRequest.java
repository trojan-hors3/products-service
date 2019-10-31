package com.insert.company.name.productsbackend.models.http;

import lombok.Getter;

import java.util.List;

/**
 * @author Callum Cooper
 */
@Getter
public class CreatePackageRequest {
    private String name;
    private String description;
    private List<String> productIds;
}
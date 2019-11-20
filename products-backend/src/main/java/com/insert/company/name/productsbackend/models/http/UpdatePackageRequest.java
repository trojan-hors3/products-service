package com.insert.company.name.productsbackend.models.http;

import com.insert.company.name.productsbackend.models.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author Callum Cooper
 */
@AllArgsConstructor
@Getter
public class UpdatePackageRequest {
    String name;
    String description;
    List<Product> products;
}

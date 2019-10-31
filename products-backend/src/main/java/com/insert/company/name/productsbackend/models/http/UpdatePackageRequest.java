package com.insert.company.name.productsbackend.models.http;

import com.insert.company.name.productsbackend.models.entities.Product;
import lombok.Getter;

import java.util.List;

/**
 * @author Callum Cooper
 */
@Getter
public class UpdatePackageRequest {
    String name;
    String description;
    List<Product> products;
}

package com.insert.company.name.productsbackend.models.entities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PackageSpec {

    @Test
    public void constructorSpec() {
        System.out.println("A package constructor should initialise a package object with the total price equal to the total product price");

        List<Product> actualProducts = new ArrayList<>();

        actualProducts.add(new Product("prod-1", "first-product", 100));
        actualProducts.add(new Product("prod-2", "second-product", 423));
        actualProducts.add(new Product("prod-3", "third-product", 999));

        Package actualPackage = new Package(UUID.randomUUID().toString(), "three-product-package", "A three product package", actualProducts);
        Double expectedPackagePrice = 1522.00;

        assertThat(actualPackage.getPrice()).isEqualTo(expectedPackagePrice);
    }
}

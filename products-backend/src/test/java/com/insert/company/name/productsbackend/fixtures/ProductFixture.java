package com.insert.company.name.productsbackend.fixtures;

import com.fasterxml.jackson.core.type.TypeReference;
import com.insert.company.name.productsbackend.models.entities.Product;
import com.insert.company.name.productsbackend.utils.StubMappedSpec;

import java.util.List;

/**
 * @author Callum Cooper
 */
public class ProductFixture extends StubMappedSpec {

    public Product singleProduct() throws Exception {
        return loadStubMapping("/stubMappings/products-api-single-product-response-v1.0.0.json", Product.class);
    }

    public List<Product> products() throws Exception {
        final TypeReference<List<Product>> productTypeRef = new TypeReference<List<Product>>() {};

        return loadStubMappings("/stubMappings/products-api-all-products-response-v1.0.0.json", productTypeRef);
    }
}

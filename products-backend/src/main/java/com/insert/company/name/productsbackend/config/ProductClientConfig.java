package com.insert.company.name.productsbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Callum Cooper
 */
@Configuration
@Getter
public class ProductClientConfig {

    @Value("${product.client.username:user}")
    private String username;

    /**
     * TODO: In a production grade system this password should never be exposed
     *  in source control. A secret based solution with automation as an
     *  alternative would be more sophisticated.
     */
    @Value("${product.client.password:pass}")
    private String password;

    @Value("${product.client.url:https://product-service.herokuapp.com/api/v1/products}")
    private String url;
}

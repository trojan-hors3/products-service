package com.insert.company.name.productsbackend.clients;

import com.insert.company.name.productsbackend.config.ProductClientConfig;
import com.insert.company.name.productsbackend.models.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * @author Callum Cooper
 */
@Component
public class ProductClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductClientConfig productClientConfig;

    ParameterizedTypeReference<List<Product>> productTypeRef = new ParameterizedTypeReference<List<Product>>() {};

    public Product getProduct(String productId) {
        final ResponseEntity<Product> response = restTemplate
                .exchange(productClientConfig.getUrl() + "/" + productId, GET, new HttpEntity<>(createRequestHeaders()), Product.class);

        return response.getBody();
    }

    public List<Product> getProducts() {
        final ResponseEntity<List<Product>> response = restTemplate
                .exchange(productClientConfig.getUrl(), GET, new HttpEntity<>(createRequestHeaders()), productTypeRef);

        return response.getBody();
    }

    private HttpHeaders createRequestHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        final String basicAuthPattern = productClientConfig.getUsername() + ":" + productClientConfig.getPassword();
        final String basicAuthEncoded = Base64.getEncoder().encodeToString(basicAuthPattern.getBytes(StandardCharsets.UTF_8));

        httpHeaders.add(AUTHORIZATION, "Basic " + basicAuthEncoded);

        return httpHeaders;
    }
}
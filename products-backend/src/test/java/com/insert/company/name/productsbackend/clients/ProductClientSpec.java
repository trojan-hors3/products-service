package com.insert.company.name.productsbackend.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.insert.company.name.productsbackend.config.ProductClientConfig;
import com.insert.company.name.productsbackend.fixtures.ProductFixture;
import com.insert.company.name.productsbackend.models.entities.Product;
import com.insert.company.name.productsbackend.utils.StubMappedSpec;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Callum Cooper
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductClientSpec extends ProductFixture {

    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT_CLIENT_URL = "https://product-service.herokuapp.com/api/v1/products";
    private static final String PRODUCT_CLIENT_USERNAME = "user";
    private static final String PRODUCT_CLIENT_PASSWORD = "pass";

    @Mock
    RestTemplate restTemplate;

    @Mock
    ProductClientConfig productClientConfig;

    @InjectMocks
    ProductClient productClient;

    @Before
    public void setup() throws Exception {
        final ResponseEntity singleProductResponse = Mockito.mock(ResponseEntity.class);

        Mockito.when(productClientConfig.getUrl()).thenReturn(PRODUCT_CLIENT_URL);
        Mockito.when(productClientConfig.getUsername()).thenReturn(PRODUCT_CLIENT_USERNAME);
        Mockito.when(productClientConfig.getPassword()).thenReturn(PRODUCT_CLIENT_PASSWORD);

        Mockito.when(singleProductResponse.getBody()).thenReturn(singleProduct());
        Mockito.when(restTemplate.exchange(
                Mockito.eq(PRODUCT_CLIENT_URL + "/" + PRODUCT_ID),
                Mockito.eq(HttpMethod.GET),
                Mockito.any(HttpEntity.class),
                Mockito.eq(Product.class)
        )).thenReturn(singleProductResponse);

        final ResponseEntity allProductsResponse = Mockito.mock(ResponseEntity.class);

        Mockito.when(allProductsResponse.getBody()).thenReturn(products());
        Mockito.when(restTemplate.exchange(
                Mockito.eq(PRODUCT_CLIENT_URL),
                Mockito.eq(HttpMethod.GET),
                Mockito.any(HttpEntity.class),
                Mockito.any(ParameterizedTypeReference.class)
        )).thenReturn(allProductsResponse);
    }

    @Test
    public void getProductSpec() throws Exception {
        System.out.println("* getProduct() should get a single product from the remote products API");

        final Product actualProduct = productClient.getProduct(PRODUCT_ID);
        final Product expectedProduct = new Product("VqKb4tyj9V6i", "Shield", 1149);

        assertThat(actualProduct.getId()).isEqualTo(expectedProduct.getId());
        assertThat(actualProduct.getName()).isEqualTo(expectedProduct.getName());
        assertThat(actualProduct.getPrice()).isEqualTo(expectedProduct.getPrice());
    }

    @Test
    public void getProductsSpec() throws Exception {
        System.out.println("* getProducts() should get all products from the remote products API");

        final List<Product> actualProducts = productClient.getProducts();

        assertThat(actualProducts.size()).isEqualTo(9);
    }
}

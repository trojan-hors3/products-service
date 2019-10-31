package com.insert.company.name.productsbackend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Callum Cooper
 */
@Configuration
@Getter
public class ExchangeRateClientConfig {

    @Value("${exchange.rate.client.url:https://api.exchangeratesapi.io/latest}")
    private String url;
}
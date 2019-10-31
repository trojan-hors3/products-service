package com.insert.company.name.productsbackend.clients;

import com.insert.company.name.productsbackend.config.ExchangeRateClientConfig;
import com.insert.company.name.productsbackend.models.http.GetExchangeRatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Callum Cooper
 */
@Component
public class ExchangeRateClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExchangeRateClientConfig exchangeRateClientConfig;

    public GetExchangeRatesResponse getExchangeRates(String baseCountryAbbreviation) {
        String url = exchangeRateClientConfig.getUrl() + "?base=" + baseCountryAbbreviation;

        return restTemplate.getForObject(url, GetExchangeRatesResponse.class);
    }
}

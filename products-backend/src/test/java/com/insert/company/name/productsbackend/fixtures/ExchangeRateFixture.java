package com.insert.company.name.productsbackend.fixtures;

import com.insert.company.name.productsbackend.models.http.GetExchangeRatesResponse;
import com.insert.company.name.productsbackend.utils.StubMappedSpec;

/**
 * @author Callum Cooper
 */
public class ExchangeRateFixture extends StubMappedSpec {

    public final String EXCHANGE_RATE_CLIENT_URL = "https://api.exchangeratesapi.io/latest";
    public final String UNITED_STATES_DOLLAR_ABBREVIATION = "USD";
    public final String GREAT_BRITISH_POUND_ABBREVIATION = "GBP";
    public final String CANADIAN_DOLLAR_ABBREVIATION = "CAD";
    public final String THAI_BAHT_ABBREVIATION = "THB";
    public final String UNKNOWN_ABBREVIATION = "UNKNOWN";

    public GetExchangeRatesResponse getExchangeRatesResponse() throws Exception {
        return loadStubMapping("/stubMappings/exchange-rate-api-latest-rates-v1.0.0.json", GetExchangeRatesResponse.class);
    }
}
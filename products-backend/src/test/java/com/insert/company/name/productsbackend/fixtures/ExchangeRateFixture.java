package com.insert.company.name.productsbackend.fixtures;

import com.insert.company.name.productsbackend.models.http.GetExchangeRatesResponse;
import com.insert.company.name.productsbackend.utils.StubMappedSpec;

/**
 * @author Callum Cooper
 */
public class ExchangeRateFixture extends StubMappedSpec {

    protected final String EXCHANGE_RATE_CLIENT_URL = "https://api.exchangeratesapi.io/latest";
    protected final String UNITED_STATES_DOLLAR_ABBREVIATION = "USD";
    protected final String GREAT_BRITISH_POUND_ABBREVIATION = "GBP";
    protected final String CANADIAN_DOLLAR_ABBREVIATION = "CAD";
    protected final String THAI_BAHT_ABBREVIATION = "THB";
    protected final String UNKNOWN_ABBREVIATION = "UNKNOWN";

    protected GetExchangeRatesResponse getExchangeRatesResponse() throws Exception {
        return loadStubMapping("/stubMappings/exchange-rate-api-latest-rates-v1.0.0.json", GetExchangeRatesResponse.class);
    }
}

package com.insert.company.name.productsbackend.models.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

/**
 * @author Callum Cooper
 */
@Getter
public class GetExchangeRatesResponse {

    @JsonProperty("base")
    private String baseRate;

    @JsonProperty("date")
    private String queryDate;

    @JsonProperty("rates")
    private Map<String, Double> rates;
}

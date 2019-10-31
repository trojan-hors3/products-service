package com.insert.company.name.productsbackend.services;

import com.insert.company.name.productsbackend.clients.ExchangeRateClient;
import com.insert.company.name.productsbackend.models.http.GetExchangeRatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author Callum Cooper
 */
@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateClient exchangeRateClient;

    public double exchange(double currentPrice, String baseCountry, String currency) {
        final BigDecimal exchangeRate = getExchangeRate(baseCountry, currency);
        final BigDecimal newPrice = exchangeRate.multiply(BigDecimal.valueOf(currentPrice));

        return newPrice.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
    }

    private BigDecimal getExchangeRate(String baseCountry, String currency) {
        final GetExchangeRatesResponse exchangeRates = exchangeRateClient.getExchangeRates(baseCountry);
        final Double exchangeRate = exchangeRates.getRates().get(currency.toUpperCase());

        if (exchangeRate == null)
            throw new RuntimeException(String.format("Unable to find exchange rate for currency %s", currency));

        return BigDecimal.valueOf(exchangeRate);
    }
}

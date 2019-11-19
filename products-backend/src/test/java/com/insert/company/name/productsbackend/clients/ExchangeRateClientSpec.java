package com.insert.company.name.productsbackend.clients;

import com.insert.company.name.productsbackend.config.ExchangeRateClientConfig;
import com.insert.company.name.productsbackend.fixtures.ExchangeRateFixture;
import com.insert.company.name.productsbackend.models.http.GetExchangeRatesResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Callum Cooper
 */
@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateClientSpec extends ExchangeRateFixture {

    @Mock
    RestTemplate restTemplate;

    @Mock
    ExchangeRateClientConfig exchangeRateClientConfig;

    @InjectMocks
    ExchangeRateClient exchangeRateClient;

    @Before
    public void setup() throws Exception {
        Mockito.when(restTemplate.getForObject(String.format(EXCHANGE_RATE_CLIENT_URL + "?base=%s", UNITED_STATES_DOLLAR_ABBREVIATION), GetExchangeRatesResponse.class))
                .thenReturn(getExchangeRatesResponse());

        Mockito.when(exchangeRateClientConfig.getUrl())
                .thenReturn(EXCHANGE_RATE_CLIENT_URL);
    }

    @Test
    public void getExchangeRateSpec() {
        System.out.println("* Get exchange rates should get current foreign exchange rates that are relative to the given base country");

        final GetExchangeRatesResponse actualResponse = exchangeRateClient.getExchangeRates(UNITED_STATES_DOLLAR_ABBREVIATION);
        final LocalDate actualDate = LocalDate.parse(actualResponse.getQueryDate());

        final LocalDate expectedDate = LocalDate.now();

        assertThat(actualDate).isEqualTo(expectedDate);
        assertThat(actualResponse.getBaseRate()).isEqualTo(UNITED_STATES_DOLLAR_ABBREVIATION);
        assertThat(actualResponse.getRates().get("EUR")).isEqualTo(0.9004141905);
    }
}

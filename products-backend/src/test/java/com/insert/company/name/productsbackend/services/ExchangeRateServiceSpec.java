package com.insert.company.name.productsbackend.services;

import com.insert.company.name.productsbackend.clients.ExchangeRateClient;
import com.insert.company.name.productsbackend.fixtures.ExchangeRateFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Callum Cooper
 */
@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateServiceSpec extends ExchangeRateFixture {

    @Mock
    ExchangeRateClient exchangeRateClient;

    @InjectMocks
    ExchangeRateService exchangeRateService;

    @Before
    public void setup() throws Exception {
        Mockito.when(exchangeRateClient.getExchangeRates(UNITED_STATES_DOLLAR_ABBREVIATION))
                .thenReturn(getExchangeRatesResponse());
    }

    @Test
    public void exchangeSpec() {
        System.out.println("* Exchange should exchange 1.0 United States Dollar to 0.78 Great British Pound");

        final double actualExchange = exchangeRateService.exchange(1.0, UNITED_STATES_DOLLAR_ABBREVIATION, GREAT_BRITISH_POUND_ABBREVIATION);
        final double expectedExchange = 0.78;

        assertThat(actualExchange).isEqualTo(expectedExchange);
    }

    @Test
    public void exchangeSpec02() {
        System.out.println("* Exchange should exchange 2.0 United States Dollars to 2.62 Canadian Dollars");

        final double actualExchange = exchangeRateService.exchange(2.0, UNITED_STATES_DOLLAR_ABBREVIATION, CANADIAN_DOLLAR_ABBREVIATION);
        final double expectedExchange = 2.62;

        assertThat(actualExchange).isEqualTo(expectedExchange);
    }

    @Test
    public void exchangeSpec03() {
        System.out.println("* Exchange should exchange 100.0 United States Dollars to 3023.60 Thai Baht");

        final double actualExchange = exchangeRateService.exchange(100.0, UNITED_STATES_DOLLAR_ABBREVIATION, THAI_BAHT_ABBREVIATION);
        final double expectedExchange = 3023.60;

        assertThat(actualExchange).isEqualTo(expectedExchange);
    }

    @Test
    public void exchangeSpec04() {
        System.out.println("* Exchange should exchange 1.0 United States Dollar to 1.0 United States Dollar");

        final double actualExchange = exchangeRateService.exchange(1.0, UNITED_STATES_DOLLAR_ABBREVIATION, UNITED_STATES_DOLLAR_ABBREVIATION);
        final double expectedExchange = 1.0;

        assertThat(actualExchange).isEqualTo(expectedExchange);
    }

    @Test(expected = RuntimeException.class)
    public void exchangeSpec05() {
        System.out.println("* Exchange should throw a runtime exception when given an unsupported currency");

        exchangeRateService.exchange(1.0, UNITED_STATES_DOLLAR_ABBREVIATION, UNKNOWN_ABBREVIATION);
    }
}
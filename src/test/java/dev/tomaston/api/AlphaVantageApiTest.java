package dev.tomaston.api;

import dev.tomaston.config.AppConfig;
import org.apache.http.client.methods.HttpGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlphaVantageApiTest {

    private AlphaVantageApi alphaVantageApi;
    private final String avBaseUrl = "https://www.alphavantage.co/query";

    @BeforeEach
    void setup() {
        AppConfig appConfig = new AppConfig();
        alphaVantageApi = new AlphaVantageApi(appConfig);
    }

    /**
     * Assert the AV base url is as expected
     */
    @Test
    void shouldReturnBaseUrl() {
        assertEquals(alphaVantageApi.getBaseUrl(), avBaseUrl);
    }

    /**
     * Assert that the API has been set
     */
    @Test
    void shouldSetAvApiKeyToNotNullValue() {
        assert(!alphaVantageApi.isApiKeyNull());
    }

    @Test
    void shouldReturn() {

        alphaVantageApi.getClientTickerData("QQQ");
    }



}
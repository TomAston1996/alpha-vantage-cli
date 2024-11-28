package dev.tomaston.api;

import dev.tomaston.config.AppConfig;
import dev.tomaston.dto.TickerDataDTO;
import dev.tomaston.entities.AVTickerData;
import dev.tomaston.mappers.AVTickerDataMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/** Alpha Vantage API */
public class AlphaVantageApi implements IStocksApi {
    private final String AV_API_KEY;
    private final String BASE_URL = "https://www.alphavantage.co/query";

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * Constructor class for Alpha Vantage 3rd Party API
     * @param appConfig instance of AppConfig
     */
    public AlphaVantageApi(AppConfig appConfig) {
        this.AV_API_KEY = appConfig.getAvApiKey();
    }

    /**
     * Clients facing method for retrieving best match ticker data
     * @param tickerSymbol i.e. QQQ
     * @return list of ticker data transfer objects
     */
    @Override
    public List<TickerDataDTO> getClientTickerData(String tickerSymbol) {
        AVTickerData avTickerData = getAVTickerData(tickerSymbol);
        return AVTickerDataMapper.avToTickerDTO(avTickerData);
    }

    /**
     * Private method for calling the AV rest api with HttpClient
     * @param tickerSymbol i.e. QQQ
     * @return unfiltered ticker data from the AV api
     */
    private AVTickerData getAVTickerData(String tickerSymbol) {
        HttpGet httpGet = new HttpGet(BASE_URL);

        //build HTTP request URI with query parameters
        buildTickerRequestUri(tickerSymbol, httpGet);

        //execute the GET query to AV API
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return AVTickerDataMapper.avTickerResponseToPojo(entity);
        } catch (IOException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Builds the URL required to request ticker data including adding the API key
     * @param tickerSymbol i.e. QQQ
     * @param httpGet HttpGet object
     */
    private void buildTickerRequestUri(String tickerSymbol, HttpGet httpGet) {
        try {
            URI uri = new URIBuilder(httpGet.getURI())
                    .addParameter("function", "SYMBOL_SEARCH")
                    .addParameter("keywords", tickerSymbol)
                    .addParameter("apikey", AV_API_KEY)
                    .build();
            httpGet.setURI(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the base URL for AV API - used for testing
     * @return base url
     */
    public String getBaseUrl() {
        return BASE_URL;
    }

    /**
     * Check is the api key has been set or not
     * @return boolean -> true if not set, false if set
     */
    public boolean isApiKeyNull() {
        return AV_API_KEY == null;
    }
}

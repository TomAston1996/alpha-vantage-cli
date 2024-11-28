package dev.tomaston.api;

import dev.tomaston.dto.TickerDataDTO;

import java.util.List;

public interface IStocksApi {
    /**
     * Clients facing method for retrieving best match ticker data
     * @param tickerSymbol i.e. QQQ
     * @return list of ticker data transfer objects
     */
    public List<TickerDataDTO> getClientTickerData(String tickerSymbol);
}

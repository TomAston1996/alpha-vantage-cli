package dev.tomaston.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tomaston.dto.TickerDataDTO;
import dev.tomaston.entities.AVTickerData;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AVTickerDataMapper {

    private final static ObjectMapper mapper = new ObjectMapper();

    public static List<TickerDataDTO> avToTickerDTO(AVTickerData avTickerData) {
        List<TickerDataDTO> clientDto = new ArrayList<>();
        avTickerData.bestMatches.forEach((item) -> {
            TickerDataDTO tickerDataDTO = new TickerDataDTO();
            tickerDataDTO.symbol = item.symbol;
            tickerDataDTO.name = item.name;
            clientDto.add(tickerDataDTO);
        });
        return clientDto;
    }

    public static AVTickerData avTickerResponseToPojo(HttpEntity entity) {
        try {
            return mapper.readValue(entity.getContent(), AVTickerData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package dev.tomaston.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AVTickerItem {
    @JsonProperty("1. symbol")
    public String symbol;

    @JsonProperty("2. name")
    public String name;

    @JsonProperty("3. type")
    public String type;

    @JsonProperty("4. region")
    public String region;

    @JsonProperty("8. currency")
    public String currency;
}

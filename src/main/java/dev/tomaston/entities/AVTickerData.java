package dev.tomaston.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AVTickerData {
    @JsonProperty("bestMatches")
    public List<AVTickerItem> bestMatches;
}

package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketDTO {

    private final long ticketmasterMarketId;
    private final String marketName;

    public MarketDTO(@JsonProperty("ticketmasterMarketId") long ticketmasterMarketId,
                     @JsonProperty("marketName") String marketName) {
        this.ticketmasterMarketId = ticketmasterMarketId;
        this.marketName = marketName;
    }

    public long getTicketmasterMarketId() {
        return ticketmasterMarketId;
    }

    public String getMarketName() {
        return marketName;
    }
}

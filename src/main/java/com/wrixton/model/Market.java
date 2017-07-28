package com.wrixton.model;

import com.wrixton.dtos.MarketDTO;

public class Market implements Model<MarketDTO>{

    private final long ticketmasterMarketId;
    private final String marketName;

    public Market(long ticketmasterMarketId, String marketName) {
        this.ticketmasterMarketId = ticketmasterMarketId;
        this.marketName = marketName;
    }

    @Override
    public MarketDTO toDTO() {
        return new MarketDTO(ticketmasterMarketId, marketName);
    }
}

package com.wrixton.model;

import com.wrixton.dtos.MarketDTO;

public class Market implements Model<MarketDTO>{

    private final Long id;
    private final long ticketmasterMarketId;
    private final String marketName;

    public Market(Long id, long ticketmasterMarketId, String marketName) {
        this.id = id;
        this.ticketmasterMarketId = ticketmasterMarketId;
        this.marketName = marketName;
    }

    @Override
    public MarketDTO toDTO() {
        return new MarketDTO(ticketmasterMarketId, marketName);
    }

    public static Market fromDTO(MarketDTO dto){
        return new Market(null, dto.getTicketmasterMarketId(), dto.getMarketName());
    }

    public Long getId() {
        return id;
    }

    public long getTicketmasterMarketId() {
        return ticketmasterMarketId;
    }

    public String getMarketName() {
        return marketName;
    }
}

package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.wrixton.dao.MarketDAO;
import com.wrixton.dtos.MarketDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/market")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Market {

    private final MarketDAO marketDAO;

    public Market(MarketDAO marketDAO) {
        this.marketDAO = marketDAO;
    }

    @GET
    @Path("{marketName}")
    @Timed
    public String getMarketName(@PathParam("marketName") String marketName) throws Exception {
        return "";
    }

    @POST
    @Timed
    public String addMarket(MarketDTO market) throws Exception {
        return "";
    }

}

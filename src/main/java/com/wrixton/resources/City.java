package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.wrixton.dao.CityTeamsDAO;
import com.wrixton.model.CityTeams;
import com.wrixton.model.TeamInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/city")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class City {

    private final CityTeamsDAO cityTeamsDAO;

    public City(CityTeamsDAO cityTeamsDAO) {
        this.cityTeamsDAO = cityTeamsDAO;
    }

    @GET
    @Path("{cityName}")
    @Timed
    public CityTeams getByTeamName(@PathParam("cityName") String cityName) throws Exception {
        List<String> teams = cityTeamsDAO.getCityTeams(cityName);
        return new CityTeams(cityName, teams);
    }

}

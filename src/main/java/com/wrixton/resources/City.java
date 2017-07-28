package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.discovery.model.Date;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Venue;
import com.wrixton.dao.CityTeamsDAO;
import com.wrixton.dao.TeamScheduleDAO;
import com.wrixton.dtos.CityTeamDTO;
import com.wrixton.dtos.EventTypeDTO;
import com.wrixton.dtos.LocationDTO;
import com.wrixton.dtos.TeamScheduleDTO;
import com.wrixton.model.CityTeam;
import com.wrixton.model.CityTeams;
import com.wrixton.model.TeamInfo;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/city")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class City {

    private static final Logger logger = LoggerFactory.getLogger(City.class);

    private final CityTeamsDAO cityTeamsDAO;
    private final TeamScheduleDAO teamScheduleDAO;
    private final DiscoveryApi ticketmasterApi;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ");

    public City(CityTeamsDAO cityTeamsDAO, TeamScheduleDAO teamScheduleDAO, DiscoveryApi ticketmasterApi) {
        this.cityTeamsDAO = cityTeamsDAO;
        this.teamScheduleDAO = teamScheduleDAO;
        this.ticketmasterApi = ticketmasterApi;
    }

    @GET
    @Path("{cityName}")
    @Timed
    public CityTeams getByCityName(@PathParam("cityName") String cityName) throws Exception {
        List<String> teams = cityTeamsDAO.getCityTeams(cityName);
        return new CityTeams(cityName, teams);
    }

    @POST
    @Path("/add")
    @Timed
    public CityTeamDTO addTeamInfo(CityTeamDTO cityTeam) throws Exception {
        TeamInfo teamInfo = teamScheduleDAO.getTeamSchedule(cityTeam.getTeamName());
        return cityTeamsDAO.addCityTeam(new CityTeam(cityTeam.getCityName(), teamInfo.getTeamId())) == 1 ? cityTeam : null;
    }

    @GET
    @Path("{cityName}/schedule")
    @Timed
    public List<TeamScheduleDTO> getCitySchedule(@PathParam("cityName") String cityName) throws Exception {
        String marketId =   "42";
        try {
            DateTime today = new DateTime().withTimeAtStartOfDay();
            String todayString = dateTimeFormatter.print(today);
            String tomorrowString = dateTimeFormatter.print(today.plusDays(1));
            SearchEventsOperation searchEventsOperation = new SearchEventsOperation()
                    .marketId(marketId) //to change
                    .startDateTime(todayString)
                    .endDateTime(tomorrowString);
            List<Event> events = ticketmasterApi.searchEvents(searchEventsOperation).getContent().getEvents();
            return events.stream()
                    .filter(current -> current.getDates().getStart().getLocalTime() != null)
                    .map(current -> {
                        Date.Start start = current.getDates().getStart();
                        LocalDate localDate = LocalDate.parse(start.getLocalDate());
                        LocalTime localTime = LocalTime.parse(start.getLocalTime());
                        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                        String eventType = current.getClassifications().stream().findFirst().map(c -> c.getSegment().getName()).get();
                        Venue venue = current.getVenues().stream().findFirst().get();
                        Venue.Location venueLocation = venue.getLocation();
                        LocationDTO location = new LocationDTO(venue.getName(), Double.valueOf(venueLocation.getLatitude()), Double.valueOf(venueLocation.getLongitude()));
                        URL eventUrl = null;
                        try {
                            eventUrl = new URL(current.getUrl());
                        } catch (MalformedURLException e){
                            logger.warn(String.format("url from ticketmaster (%s) was not formatted properly: %s", current.getUrl(), e));
                        }
                        return new TeamScheduleDTO(current.getName(), eventUrl, localDateTime, EventTypeDTO.map(eventType), location);
                    }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}

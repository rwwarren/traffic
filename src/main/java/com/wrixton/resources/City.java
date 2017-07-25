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
import com.wrixton.dtos.LocationDTO;
import com.wrixton.dtos.TeamScheduleDTO;
import com.wrixton.model.CityTeam;
import com.wrixton.model.CityTeams;
import com.wrixton.model.TeamInfo;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    private final CityTeamsDAO cityTeamsDAO;
    private final TeamScheduleDAO teamScheduleDAO;
    private final DiscoveryApi ticketmasterApi;

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
//        CityTeams teams = getByCityName(cityName);
        List<TeamScheduleDTO> events = getEvents();
        return events;
    }

    private List<TeamScheduleDTO> getEvents(){
        try {
            DateTime today = new DateTime().withTimeAtStartOfDay();
            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZZ");
            String todayString = format.print(today);
            String tomorrowString = format.print(today.plusDays(1));
            SearchEventsOperation searchEventsOperation = new SearchEventsOperation()
                    .marketId("42") //to change
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
                        Venue.Location venueLocation = current.getVenues().stream().findFirst().get().getLocation();
                        LocationDTO location = new LocationDTO(Double.valueOf(venueLocation.getLatitude()), Double.valueOf(venueLocation.getLongitude()));
                        return new TeamScheduleDTO(current.getName(), localDateTime, location);
                    }).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            return new ArrayList<>();
        }
    }

}

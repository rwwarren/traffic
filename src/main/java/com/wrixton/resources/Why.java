package com.wrixton.resources;

import com.wrixton.dtos.EventDTO;
import com.codahale.metrics.annotation.Timed;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.wrixton.manager.EventManager;
import com.wrixton.model.Event;
import com.wrixton.request.FutureLocationRequest;
import com.wrixton.request.LocationRequest;

@Path("/why")
@Produces(MediaType.APPLICATION_JSON)
public class Why {

    private final EventManager eventManager;

    public Why(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @GET
    @Path("{city}")
    @Timed
    //TODO convert city to location
    public List<EventDTO> getEventsInCity(@PathParam("city") String city) {
        //gametonight.in
        //twitter
        //google maps
        return eventManager.getEvents(city).stream().map(Event::toDTO).collect(Collectors.toList());
    }


    @POST
    @Path("/between")
    @Timed
    public List<EventDTO> getEventsBetweenLocations(@Valid LocationRequest locationRequest) {
//        return Response.ok("works").build();
        return new ArrayList<>();
    }

    @POST
    @Path("/between/future")
    @Timed
    public List<EventDTO> getEventsBetweenLocationsFuture(@Valid FutureLocationRequest futureLocationRequest) {
//        return Response.ok("works").build();
        return new ArrayList<>();
    }


}
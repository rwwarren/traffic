package model;

import dtos.EventDTO;
import dtos.LocationDTO;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event {

    //Add amount attendees?
    private final String name;
    private final String description;
    private final LocalDate date;
    private final LocalTime time;
    private final Location location;

    public Event(String name, String description, LocalDate date, LocalTime time, Location location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public EventDTO toDTO() {
        final LocationDTO locationDTO = location == null ? null : location.toDTO();
        return new EventDTO(name, description, date, time, locationDTO);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }
}

package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {

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

}

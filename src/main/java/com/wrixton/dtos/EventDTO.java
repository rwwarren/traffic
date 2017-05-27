package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDTO {

    private final String name;
    private final String description;
    private final LocalDate date;
    private final LocalTime time;
    private final LocationDTO location;

    public EventDTO(@JsonProperty("name") String name,
                    @JsonProperty("description") String description,
                    @JsonProperty("date") LocalDate date,
                    @JsonProperty("time") LocalTime time,
                    @JsonProperty("location") LocationDTO location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
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

    public LocationDTO getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDTO eventDTO = (EventDTO) o;
        return Objects.equals(name, eventDTO.name) &&
                Objects.equals(description, eventDTO.description) &&
                Objects.equals(date, eventDTO.date) &&
                Objects.equals(time, eventDTO.time) &&
                Objects.equals(location, eventDTO.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, date, time, location);
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location=" + location +
                '}';
    }
}

package com.wrixton.dtos.scrape_dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowBoxEventDTO {

    private final LocalDateTime eventTime;
    private final String venue;
    private final String band;

    public ShowBoxEventDTO(@JsonProperty("eventTime") LocalDateTime eventTime,
                           @JsonProperty("venue") String venue,
                           @JsonProperty("band") String band) {
        this.eventTime = eventTime;
        this.venue = venue;
        this.band = band;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public String getVenue() {
        return venue;
    }

    public String getBand() {
        return band;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowBoxEventDTO that = (ShowBoxEventDTO) o;
        return Objects.equals(eventTime, that.eventTime) &&
                Objects.equals(venue, that.venue) &&
                Objects.equals(band, that.band);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTime, venue, band);
    }

    @Override
    public String toString() {
        return "ShowBoxEventDTO{" +
                "eventTime=" + eventTime +
                ", venue='" + venue + '\'' +
                ", band='" + band + '\'' +
                '}';
    }
}

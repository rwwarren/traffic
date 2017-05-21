package DTOs.scrape_dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StrangerEventDTO {
    private final String title;
    private final String category;
    private final String location;
    private final LocalDate eventDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public StrangerEventDTO(@JsonProperty("title") String title,
                            @JsonProperty("category") String category,
                            @JsonProperty("location") String location,
                            @JsonProperty("eventDate") LocalDate eventDate,
                            @JsonProperty("startTime") LocalTime startTime,
                            @JsonProperty("endTime") LocalTime endTime) {
        this.title = title;
        this.category = category;
        this.location = location;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StrangerEventDTO that = (StrangerEventDTO) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(category, that.category) &&
                Objects.equals(location, that.location) &&
                Objects.equals(eventDate, that.eventDate) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, category, location, eventDate, startTime, endTime);
    }

    @Override
    public String toString() {
        return "StrangerEventDTO{" +
                "title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", location='" + location + '\'' +
                ", eventDate=" + eventDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

package DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StrangerEventDTO {
    private final String title;
    private final String location;
    private final LocalDate eventDate;
    private final String time;

    public StrangerEventDTO(@JsonProperty("title") String title,
                            @JsonProperty("location") String location,
                            @JsonProperty("eventDate") LocalDate eventDate,
                            @JsonProperty("time") String time) {
        this.title = title;
        this.location = location;
        this.eventDate = eventDate;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StrangerEventDTO that = (StrangerEventDTO) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(location, that.location) &&
                Objects.equals(eventDate, that.eventDate) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, location, eventDate, time);
    }

    @Override
    public String toString() {
        return "StrangerEventDTO{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", eventDate=" + eventDate +
                ", time='" + time + '\'' +
                '}';
    }
}

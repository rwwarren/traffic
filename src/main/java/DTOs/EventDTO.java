package DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalTime;
import model.Location;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDTO {

    private final String name;
    private final String description;
    private final LocalDate date;
    private final LocalTime time;
    private final Location location;

    public EventDTO(@JsonProperty("name") String name,
                    @JsonProperty("description") String description,
                    @JsonProperty("date") LocalDate date,
                    @JsonProperty("time") LocalTime time,
                    @JsonProperty("location") Location location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
    }

}

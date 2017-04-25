package DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WsdotDTO {
    private final String description;
    private final LocalDateTime dateTime;
    private final String location;

    public WsdotDTO(@JsonProperty("description") String description,
                    @JsonProperty("dateTime") LocalDateTime dateTime,
                    @JsonProperty("location") String location) {
        this.description = description;
        this.dateTime = dateTime;
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WsdotDTO wsdotDTO = (WsdotDTO) o;
        return Objects.equals(description, wsdotDTO.description) &&
                Objects.equals(dateTime, wsdotDTO.dateTime) &&
                Objects.equals(location, wsdotDTO.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, dateTime, location);
    }

    @Override
    public String toString() {
        return "WsdotDTO{" +
                "description='" + description + '\'' +
                ", dateTime=" + dateTime +
                ", location='" + location + '\'' +
                '}';
    }
}

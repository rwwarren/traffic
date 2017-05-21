package dtos.scrape_dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WsdotDTO {

    private final String severity;
    private final String description;
    private final LocalDateTime lastUpdated;
    private final String location;

    public WsdotDTO(@JsonProperty("severity") String severity,
                    @JsonProperty("description") String description,
                    @JsonProperty("lastUpdated") LocalDateTime lastUpdated,
                    @JsonProperty("location") String location) {
        this.severity = severity;
        this.description = description;
        this.lastUpdated = lastUpdated;
        this.location = location;
    }

    public String getSeverity() {
        return severity;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WsdotDTO wsdotDTO = (WsdotDTO) o;
        return Objects.equals(severity, wsdotDTO.severity) &&
                Objects.equals(description, wsdotDTO.description) &&
                Objects.equals(lastUpdated, wsdotDTO.lastUpdated) &&
                Objects.equals(location, wsdotDTO.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(severity, description, lastUpdated, location);
    }

    @Override
    public String toString() {
        return "WsdotDTO{" +
                "severity='" + severity + '\'' +
                ", description='" + description + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", location='" + location + '\'' +
                '}';
    }
}

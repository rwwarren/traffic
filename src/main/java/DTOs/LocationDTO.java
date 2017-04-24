package DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {

    private final double latitude;
    private final double longitude;

    public LocationDTO(@JsonProperty("latitude") double latitude,
                       @JsonProperty("longitude") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

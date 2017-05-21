package request;

import dtos.LocationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FutureLocationRequest {

    private final LocalDate date;
    private final LocationDTO start;
    private final LocationDTO end;

    public FutureLocationRequest(@JsonProperty("date") LocalDate date,
                                 @JsonProperty("start") LocationDTO start,
                                 @JsonProperty("end") LocationDTO end) {
        this.date = date;
        this.start = start;
        this.end = end;
    }
}

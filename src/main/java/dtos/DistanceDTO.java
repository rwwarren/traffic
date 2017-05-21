package dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistanceDTO {

    private final String startAddress;
    private final String endAddress;

    public DistanceDTO(@JsonProperty("startAddress") String startAddress, @JsonProperty("endAddress") String endAddress) {
        this.startAddress = startAddress;
        this.endAddress = endAddress;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }
}

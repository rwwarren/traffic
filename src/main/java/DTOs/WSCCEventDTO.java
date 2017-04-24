package DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WSCCEventDTO {
    private final String eventName;
    private final Date startDate;
    private final Date endDate;
    private final Integer estAttendees;

    public WSCCEventDTO(@JsonProperty("eventName") String eventName,
                        @JsonProperty("startDate") Date startDate,
                        @JsonProperty("endDate") Date endDate,
                        @JsonProperty("estAttendees") Integer estAttendees) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estAttendees = estAttendees;
    }

    public String getEventName() {
        return eventName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getEstAttendees() {
        return estAttendees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WSCCEventDTO that = (WSCCEventDTO) o;
        return Objects.equals(eventName, that.eventName) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(estAttendees, that.estAttendees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, startDate, endDate, estAttendees);
    }

    @Override
    public String toString() {
        return "WSCCEventDTO{" +
                "eventName='" + eventName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", estAttendees=" + estAttendees +
                '}';
    }
}

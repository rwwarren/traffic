package DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameTonightTodayDTO {

    private final Map<Date, GameTonightInfoDTO> date;

    public GameTonightTodayDTO(@JsonProperty("date") Map<Date, GameTonightInfoDTO> date) {
        this.date = date;
    }
}

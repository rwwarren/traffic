package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameTonightDTO {

    private final boolean game_tonight;
    private final Map<String, GameTonightInfoDTO> today;


    public GameTonightDTO(@JsonProperty("game_tonight") boolean game_tonight,
                          @JsonProperty("today") Map<String, GameTonightInfoDTO> today) {
        this.game_tonight = game_tonight;
        this.today = today;
    }

    public boolean isGame_tonight() {
        return game_tonight;
    }

    public Map<String, GameTonightInfoDTO> getToday() {
        return today;
    }
}

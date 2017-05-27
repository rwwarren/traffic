package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameTonightKeyDTO {

    private final GameTonightInfoDTO game;

    public GameTonightKeyDTO(@JsonProperty("game") GameTonightInfoDTO game) {
        this.game = game;
    }

}

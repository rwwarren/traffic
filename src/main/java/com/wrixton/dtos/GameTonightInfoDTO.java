package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameTonightInfoDTO {

    private final String title;
    private final LocalDateTime date;
    private final String key;
    private final String type;

    public GameTonightInfoDTO(@JsonProperty("title") String title,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                              @JsonProperty("date") LocalDateTime date,
                              @JsonProperty("key") String key,
                              @JsonProperty("type") String type) {
        this.title = title;
        this.date = date;
        this.key = key;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }
}

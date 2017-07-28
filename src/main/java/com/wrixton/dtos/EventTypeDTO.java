package com.wrixton.dtos;

public enum EventTypeDTO {
    SPORT,
    CONCERT,
    FESTIVAL,
    ARTS_THEATRE,
    UNKNOWN;

    public static EventTypeDTO map(String type) {
        switch (type.toLowerCase()) {
            case "music":
                return CONCERT;
            case "sports":
                return SPORT;
            case "arts & theatre":
                return ARTS_THEATRE;
            default:
                return UNKNOWN;
        }
    }
}

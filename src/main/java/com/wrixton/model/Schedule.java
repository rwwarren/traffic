package com.wrixton.model;

import com.wrixton.dtos.ScheduleDTO;

public class Schedule implements Model<ScheduleDTO> {

    private final Long id;
    private final String teamName;
    private final String eventUrl;
    private final String eventDateTime;
    private final String eventType;
    private final String locationName;
    private final Long eventMarket;
    private final double latitude;
    private final double longitude;

    public Schedule(Long id, String teamName, String eventUrl, String eventDateTime, String eventType, String locationName, Long eventMarket, double latitude, double longitude) {
        this.id = id;
        this.teamName = teamName;
        this.eventUrl = eventUrl;
        this.eventDateTime = eventDateTime;
        this.eventType = eventType;
        this.locationName = locationName;
        this.eventMarket = eventMarket;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    @Override
    public ScheduleDTO toDTO() {
        return new ScheduleDTO(teamName, eventUrl, eventDateTime, eventType, locationName, latitude, longitude);
    }

}

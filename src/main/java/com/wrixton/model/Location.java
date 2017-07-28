package com.wrixton.model;

import com.wrixton.dtos.LocationDTO;

public class Location {

    private final String locationName;
    private final double latitude;
    private final double longitude;

    public Location(String locationName, double latitude, double longitude) {
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationDTO toDTO() {
        return new LocationDTO(this.locationName, this.latitude, this.longitude);
    }
}

package model;

import dtos.LocationDTO;

public class Location {

    private final double latitude;
    private final double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationDTO toDTO() {
        return new LocationDTO(this.latitude, this.longitude);
    }
}

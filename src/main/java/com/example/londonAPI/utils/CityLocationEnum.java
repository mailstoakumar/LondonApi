package com.example.londonAPI.utils;

import java.util.Arrays;

public enum CityLocationEnum {

    LONDON("London", 51.5074, -0.1278),
    BIRMINGHAM("Birmingham", 52.4862, -1.8904),
    MANCHESTER("Manchester", 53.4830, -2.2446);

    private final String cityName;
    private final double latitude;
    private final double longitude;

    CityLocationEnum(String cityName, double latitude, double longitude) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static CityLocationEnum getByCityName(String cityName) {
        return Arrays.stream(values())
                .filter(city -> city.getCityName().equalsIgnoreCase(cityName))
                .findFirst()
                .orElse(null);
    }

    public String getCityName() {
        return cityName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}


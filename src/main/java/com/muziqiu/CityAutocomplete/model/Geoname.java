package com.muziqiu.CityAutocomplete.model;

import org.springframework.stereotype.Component;

@Component
public class Geoname {
    int geonameId;
    String name;
    String asciiname;
    String alternatename;
    double latitude;
    double longitude;
    long population;

    public Geoname() {
    }

    public Geoname(int geonameId, String name, String asciiname, String alternatename, double latitude, double longitude, long population) {
        this.geonameId = geonameId;
        this.name = name;
        this.asciiname = asciiname;
        this.alternatename = alternatename;
        this.latitude = latitude;
        this.longitude = longitude;

        this.population = population;

    }

    public int getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(int geonameId) {
        this.geonameId = geonameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsciiname() {
        return asciiname;
    }

    public void setAsciiname(String asciiname) {
        this.asciiname = asciiname;
    }

    public String getAlternatename() {
        return alternatename;
    }

    public void setAlternatename(String alternatename) {
        this.alternatename = alternatename;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}

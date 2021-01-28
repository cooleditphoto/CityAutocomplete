package com.muziqiu.CityAutocomplete.POJO;

import org.springframework.stereotype.Component;

@Component
public class SuggestionResponse {
    int id;
    String name;
    String asciiname;
    String alternatename;
    Long population;
    double longitude;
    double latitude;
    double score;

    public SuggestionResponse() {
    }

    public SuggestionResponse(int id, String name, String asciiname, String alternatename, Long population, double longitude, double latitude, double score) {
        this.id = id;
        this.name = name;
        this.asciiname = asciiname;
        this.alternatename = alternatename;
        this.population = population;
        this.longitude = longitude;
        this.latitude = latitude;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}

package com.muziqiu.CityAutocomplete.POJO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.muziqiu.CityAutocomplete.model.Geoname;
import org.springframework.stereotype.Component;

@Component
public class SuggestionResponse {
    int id;
    String name;
    double longitude;
    double latitude;
    double score;

    public SuggestionResponse() {
    }

    public SuggestionResponse(int id, String name, double longitude, double latitude, double score) {
        this.id = id;
        this.name = name;
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
}

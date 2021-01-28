package com.muziqiu.CityAutocomplete.util;

public class DistanceCalculation {

    //Haversine formula, return value is in kilometers
    public static double calculateDistance(double querylatitude, double querylongitude, double latitude, double longitude) {
        double R = 6371;
        double dLat = (querylatitude - latitude) * (Math.PI / 180);
        double dLon = (querylongitude - longitude) * (Math.PI / 180);
        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos((latitude) * (Math.PI / 180)) * Math.cos((querylatitude) * (Math.PI / 180)) *
                                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }
}

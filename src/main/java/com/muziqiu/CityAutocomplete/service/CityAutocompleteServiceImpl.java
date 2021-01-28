package com.muziqiu.CityAutocomplete.service;

import com.muziqiu.CityAutocomplete.DAO.GeonameEntityDAO;
import com.muziqiu.CityAutocomplete.POJO.SuggestionResponse;
import com.muziqiu.CityAutocomplete.model.Geoname;
import com.muziqiu.CityAutocomplete.util.DistanceCalculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityAutocompleteServiceImpl implements CityAutocompleteService {
    private static final Logger log = LoggerFactory.getLogger(CityAutocompleteServiceImpl.class);

    @Autowired
    private GeonameEntityDAO geonameEntityDAO;

    public List<SuggestionResponse> getSuggestedCities(String query, double latitude, double longitude) {
        List<Geoname> geonameList = geonameEntityDAO.getGeoNameByPartialNameAndLocation(query, latitude, longitude);

        List<SuggestionResponse> suggestionResponseList = geonameList.stream().map(geoname -> setSuggestionResponse(geoname, query, latitude, longitude)).collect(Collectors.toList());
        suggestionResponseList.sort(Comparator.comparing(SuggestionResponse::getScore).reversed());

        return suggestionResponseList;
    }

    public List<SuggestionResponse> getSuggestedCities(String query) {
        List<Geoname> geonameList = geonameEntityDAO.getGeoNameByPartialName(query);

        List<SuggestionResponse> suggestionResponseList = geonameList.stream().map(geoname -> setSuggestionResponse(geoname, query)).collect(Collectors.toList());
        suggestionResponseList.sort(Comparator.comparing(SuggestionResponse::getScore).reversed());

        return suggestionResponseList;
    }

    private double calculateScoreByQueryandPopulation(Geoname geoname, String query) {
        double score = 0;
        int lengthScore = geoname.getName().length() / query.length();

        if (lengthScore == 1) {
            score += 0.5;
        } else if (lengthScore == 2) {
            score += 0.3;
        } else {
            score += 0.1;
        }

        if (geoname.getPopulation() > 1000000) {
            score += 0.5;
        } else if (geoname.getPopulation() > 100000) {
            score += 0.3;
        }

        return score;
    }

    private double calculateScoreByQueryandLocationandPopulation(Geoname geoname, String query, double latitude, double longitude) {
        //scoring algorithm, if the length of the name/query = 1, then score+=0.5, if name/query = 2, then score+=0.3, if name/query/>=3, then score+=0.1
        //the distance under 5km score+=0.5, under 10km score+=0.4, under 15km score+= 0.3, under 25km score+=0.2, under 50km score+=0.1
        double score = calculateScoreByQueryandPopulation(geoname, query);

        double distance = DistanceCalculation.calculateDistance(latitude, longitude, geoname.getLatitude(), geoname.getLongitude());

        if (distance <= 5) {
            score += 0.5;
        } else if (distance <= 10) {
            score += 0.4;
        } else if (distance < 15) {
            score += 0.3;
        } else if (distance <= 25) {
            score += 0.2;
        } else if (distance <= 50) {
            score += 0.1;
        }

        //normalization
        score = Math.floor(score / 1.5 * 100) / 100;
        return score;
    }

    private SuggestionResponse setSuggestionResponse(Geoname geoname, String query, double latitude, double longitude) {
        double score = calculateScoreByQueryandLocationandPopulation(geoname, query, latitude, longitude);
        return new SuggestionResponse(geoname.getGeonameId(), geoname.getName(), geoname.getLongitude(), geoname.getLatitude(), score);
    }

    private SuggestionResponse setSuggestionResponse(Geoname geoname, String query) {
        double score = calculateScoreByQueryandPopulation(geoname, query);
        return new SuggestionResponse(geoname.getGeonameId(), geoname.getName(), geoname.getLongitude(), geoname.getLatitude(), score);
    }
}

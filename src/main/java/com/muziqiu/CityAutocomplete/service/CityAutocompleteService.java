package com.muziqiu.CityAutocomplete.service;

import com.muziqiu.CityAutocomplete.POJO.SuggestionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityAutocompleteService {

    List<SuggestionResponse> getSuggestedCities(String query, double latitude, double longitude);

    List<SuggestionResponse> getSuggestedCities(String query);
}

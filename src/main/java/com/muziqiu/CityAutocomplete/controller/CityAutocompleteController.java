package com.muziqiu.CityAutocomplete.controller;

import com.muziqiu.CityAutocomplete.POJO.RestResponse;
import com.muziqiu.CityAutocomplete.POJO.SuggestionResponse;
import com.muziqiu.CityAutocomplete.service.CityAutocompleteService;
import com.muziqiu.CityAutocomplete.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CityAutocompleteController {
    private static final Logger log = LoggerFactory.getLogger(CityAutocompleteController.class);

    @Autowired
    CityAutocompleteService cityAutocompleteService;

    @GetMapping("/suggestions")
    public RestResponse getSuggestions(@RequestParam(value = "q", required = false) String query,
                                       @RequestParam(value = "latitude", required = false) Double latitude, @RequestParam(value = "longitude", required = false) Double longitude) {
        try {
            if (query == null || "".equals(query)) {
                return RestResponse.fail(ResultCode.NO_REQUIRED_PARAM);
            }
            log.info("the request params: query name: " + query + " latitude: " + latitude + " longitude: " + longitude);
            if ((latitude != null && longitude == null) || latitude == null && longitude != null) {
                return RestResponse.fail(ResultCode.INCOMPLETE_QUERY);
            } else if (longitude != null & latitude != null) {
                List<SuggestionResponse> suggestionResponseList = cityAutocompleteService.getSuggestedCities(query, latitude, longitude);
                return RestResponse.succuess(suggestionResponseList);
            } else {
                List<SuggestionResponse> suggestionResponseList = cityAutocompleteService.getSuggestedCities(query);
                return RestResponse.succuess(suggestionResponseList);
            }
        } catch (MethodArgumentTypeMismatchException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "bad request", exc);
        }
    }
}

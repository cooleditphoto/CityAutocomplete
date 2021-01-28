package com.muziqiu.CityAutocomplete.service;

import com.muziqiu.CityAutocomplete.DAO.GeonameEntityDAO;
import com.muziqiu.CityAutocomplete.POJO.SuggestionResponse;
import com.muziqiu.CityAutocomplete.model.Geoname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class CityAutocompleteServiceTest {
    @Autowired
    private GeonameEntityDAO geonameEntityDAO;
    @Autowired
    private CityAutocompleteService cityAutocompleteService;

    @BeforeEach
    void initGeonameData() {
        geonameEntityDAO.clearGeoNames();

        Geoname geoname1 = new Geoname(1, "London", "London", "London", 43, -79, 1500000);
        geonameEntityDAO.saveGeoName(geoname1);
        Geoname geoname2 = new Geoname(2, "Montreal", "Montreal", "Montreal", 45, -73, 50000);
        geonameEntityDAO.saveGeoName(geoname2);
        Geoname geoname3 = new Geoname(3, "Montr", "Montr", "Montr", 45, -73, 2400000);
        geonameEntityDAO.saveGeoName(geoname3);
    }

    @Test
    void testGetSuggestedCitiesByName() {
        List<SuggestionResponse> suggestionResponseList = cityAutocompleteService.getSuggestedCities("montr");
        assertTrue(suggestionResponseList.size() == 2);
        assertTrue("Montr".equals(suggestionResponseList.get(0).getName()));

        for (SuggestionResponse suggestionResponse : suggestionResponseList) {
            if ("Montr".equals(suggestionResponse.getName())) {
                assertTrue(suggestionResponse.getScore() == 1.0d);
            }

            if ("Montreal".equals(suggestionResponse.getName())) {
                assertTrue(suggestionResponse.getScore() == 0.5d);
            }
        }
    }

    @Test
    void testGetSuggestedCitiesByNameAndLocation() {
        List<SuggestionResponse> suggestionResponseList = cityAutocompleteService.getSuggestedCities("London", 45d, -73d);
        assertEquals(3, suggestionResponseList.size());
        assertTrue("Montr".equals(suggestionResponseList.get(0).getName()));

        for (SuggestionResponse suggestionResponse : suggestionResponseList) {
            if ("Montr".equals(suggestionResponse.getName())) {
                assertTrue(suggestionResponse.getScore() == 0.73d);
            }

            if ("Montreal".equals(suggestionResponse.getName())) {
                assertTrue(suggestionResponse.getScore() == 0.66d);
            }

            if ("London".equals(suggestionResponse.getName())) {
                assertTrue(suggestionResponse.getScore() == 0.66d);
            }
        }
    }

}

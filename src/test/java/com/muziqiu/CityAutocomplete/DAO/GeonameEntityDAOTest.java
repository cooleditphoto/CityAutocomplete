package com.muziqiu.CityAutocomplete.DAO;

import com.muziqiu.CityAutocomplete.model.Geoname;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class GeonameEntityDAOTest {

    @Autowired
    private GeonameEntityDAO geonameEntityDAO;

    @BeforeEach
    void initGeonameData() {
        geonameEntityDAO.clearGeoNames();
        Geoname geoname1 = new Geoname(1, "London", "London", "London", 43, -79, 10000000);
        geonameEntityDAO.saveGeoName(geoname1);
        Geoname geoname2 = new Geoname(2, "Montreal", "Montreal", "Montreal", 45, -73, 50000);
        geonameEntityDAO.saveGeoName(geoname2);
    }

    @Test
    void testGetGeonamesByPartialName() {
        List<Geoname> list = geonameEntityDAO.getGeoNameByPartialName("lon");
        assertTrue(1 == list.size());
        assertTrue("London".equals(list.get(0).getName()));
    }

    @Test
    void testGetGeonamesByPartialNameandLocation() {
        List<Geoname> list = geonameEntityDAO.getGeoNameByPartialNameAndLocation("lon", 45d, -73d);
        assertTrue(2 == list.size());
        assertEquals("London,Montreal", list.stream().map(Geoname::getName).collect(Collectors.joining(",")));
    }


}

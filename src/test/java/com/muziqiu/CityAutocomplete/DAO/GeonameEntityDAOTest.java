package com.muziqiu.CityAutocomplete.DAO;

import com.muziqiu.CityAutocomplete.model.Geoname;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class GeonameEntityDAOTest {

    @Autowired
    private GeonameEntityDAO geonameEntityDAO;

    @Before
    void initGeonameData() {
        Geoname geoname1 = new Geoname(1, "London", "London", "London", 43, -79, 10000000);
        geonameEntityDAO.saveGeoName(geoname1);
        Geoname geoname2 = new Geoname(1, "Montreal", "Montreal", "Montreal", 45, -73, 50000);
        geonameEntityDAO.saveGeoName(geoname2);
    }

    @Test
    void testGetGeonamesByPartialName() {
        List<Geoname> list = geonameEntityDAO.getGeoNameByPartialName("lon");
        assertThat(1==list.size());
        assertThat("London".equals(list.get(0).getName()));
    }

    @Test
    void testGetGeonamesByPartialNameandLocation() {
        List<Geoname> list = geonameEntityDAO.getGeoNameByPartialNameAndLocation("lon",45d,-73d);
        assertThat(2==list.size());
        assertThat("London,Montreal".equals(list.stream().map(Geoname::getName).collect(Collectors.joining())));
    }


}

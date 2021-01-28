package com.muziqiu.CityAutocomplete.DAO;

import com.muziqiu.CityAutocomplete.model.Geoname;
import com.muziqiu.CityAutocomplete.util.DistanceCalculation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeonameEntityDAO {

    private final List<Geoname> geonameList = new LinkedList<>();

    public void saveGeoName(Geoname geoname) {
        geonameList.add(geoname);
    }

    public List<Geoname> getGeoNameByPartialName(String query) {
        return geonameList.stream()
                .filter(geoname -> StringUtils.containsIgnoreCase(geoname.getName(),query)).collect(Collectors.toList());
    }

    public List<Geoname> getGeoNameByPartialNameAndLocation(String query, double latitude, double longitude) {
        return geonameList.stream()
                .filter(geoname -> StringUtils.containsIgnoreCase(geoname.getName(),query)|| DistanceCalculation.calculateDistance(latitude, longitude, geoname.getLongitude(), geoname.getLatitude())<50).collect(Collectors.toList());
    }
}

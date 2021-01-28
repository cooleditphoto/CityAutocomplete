package com.muziqiu.CityAutocomplete.service;

import com.muziqiu.CityAutocomplete.DAO.GeonameEntityDAO;
import com.muziqiu.CityAutocomplete.model.Geoname;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
class InitializeDataFromFile {

    @Autowired
    GeonameEntityDAO geonameEntityDAO;

    @Bean
    void initDatabase() {
        try {
            InputStream fileInputStream;
            BufferedReader bufferedReader;
            final String filepathInSamePackage = "./CA.txt";
            fileInputStream = new FileInputStream(filepathInSamePackage);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            bufferedReader
                    .lines()
                    .map(s -> {
                        String[] splitStrings = s.split("\t", -1);
                        return new Geoname(
                                Integer.parseInt(splitStrings[0]),
                                splitStrings[1],
                                splitStrings[2],
                                splitStrings[3],
                                Double.parseDouble(splitStrings[4]),
                                Double.parseDouble(splitStrings[5]),
                                Long.parseLong(splitStrings[14].equals("") ? "0" : splitStrings[14])
                        );
                    }).forEach(list -> geonameEntityDAO.saveGeoName(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.muziqiu.CityAutocomplete.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CityAutocompleteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOnlyLatitudeorOnlyLongitude() throws Exception {
        this.mockMvc.perform(get("/suggestions").queryParam("q","lon").queryParam("longitude","-73")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("not complete")));
        this.mockMvc.perform(get("/suggestions").queryParam("q","lon").queryParam("latitude","-73")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("not complete")));
    }

    @Test
    public void testNoQuery() throws Exception {
        this.mockMvc.perform(get("/suggestions")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("no required param q")));
    }

    @Test
    public void testOnlyQuery() throws Exception {
        this.mockMvc.perform(get("/suggestions").queryParam("q","lon")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Request is successful")));
    }

    @Test
    public void testQueryandLocation() throws Exception {
        this.mockMvc.perform(get("/suggestions").queryParam("q","lon").queryParam("latitude","-43").queryParam("longitude","73")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Request is successful")));
    }

}

package com.example.londonAPI.controllers;

import com.example.londonAPI.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getUsersFromCityWithinRangeTest() throws Exception {
        String city = "London";
        double range = 100.0;
        MvcResult mvcResult = mockMvc.perform(get("/usersFromLondon"))
                .andExpect(status().isOk()).andReturn();

        String jsonResponse = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(jsonResponse, new TypeReference<List<User>>() {
        });

        assertTrue(users.size() > 0);
    }

}
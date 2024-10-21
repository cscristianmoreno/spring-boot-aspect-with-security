package com.security.spring_app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.spring_app.entity.Users;
import com.security.spring_app.reuse.UsersTest;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private Users users;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        users = UsersTest.getUser();
        users.getPersons().setId(0);
    }

    @Test
    void testSave() throws Exception {
        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders.post("/users/save")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(users));

        ResultActions resultActions = mockMvc.perform(requestBuilders);
        MvcResult result = resultActions.andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testUpdate() throws Exception {

        users.setUsername("testcris");
        users.setEmail("roulete@swk.com");

        MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders.put("/users/update")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(users));


        ResultActions resultActions = mockMvc.perform(requestBuilders);
        MvcResult result = resultActions.andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testFindById() {
        
    }

    @Test
    void testDeleteById() {

    }
}

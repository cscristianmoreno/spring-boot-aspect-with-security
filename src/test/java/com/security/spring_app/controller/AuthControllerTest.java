package com.security.spring_app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.spring_app.dto.LoginDTO;
import com.security.spring_app.reuse.AuthCompleteTest;
import com.security.spring_app.reuse.LoginDTOTest;
import com.security.spring_app.security.CustomAuthenticationManager;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomAuthenticationManager customAuthenticationManager;

    private UsernamePasswordAuthenticationToken authThatWillBeReceived;
    private UsernamePasswordAuthenticationToken authThatWillBeSended; 

    private LoginDTO loginDTO;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        authThatWillBeReceived = AuthCompleteTest.getAuth();

        authThatWillBeSended = new UsernamePasswordAuthenticationToken(
            "user",
            "password"
        );
    }

    @Test
    void testLogin() throws Exception {

        loginDTO = LoginDTOTest.getLogin();

        // GIVEN
        given(customAuthenticationManager.authenticate(authThatWillBeSended)).willReturn(authThatWillBeReceived);

        // WHEN
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/auth/login");
        builder.contentType(MediaType.APPLICATION_JSON);
        builder.accept(MediaType.APPLICATION_JSON);
        builder.content(objectMapper.writeValueAsString(loginDTO));

        ResultActions resultActions = mockMvc.perform(builder);
        MvcResult mvcResult = resultActions.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse(); 

        System.out.println(response.getContentAsString());

        // THEN
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}

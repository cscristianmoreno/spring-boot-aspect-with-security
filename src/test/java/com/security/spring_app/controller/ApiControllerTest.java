package com.security.spring_app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.security.spring_app.enums.RolesTypes;
import com.security.spring_app.services.JwkService;

@SpringBootTest
@AutoConfigureMockMvc
public class ApiControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwkService jwkService;

    private String token;

    @BeforeEach
    void setup() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
            "user",
            null,
            AuthorityUtils.createAuthorityList(RolesTypes.ADMIN.name())
        );

        token = jwkService.encode(authentication);
    }

    @Test
    void testGetPrivateKeyWithAdmin() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/private-key");
        builder.header(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        ResultActions resultActions = mockMvc.perform(builder);
        MvcResult mvcResult = resultActions.andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void testGetPublicKey() {
        
    }

    
}

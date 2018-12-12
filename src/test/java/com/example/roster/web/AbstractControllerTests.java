package com.example.roster.web;

import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/*
 * All the controller tests extend this class.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Value("test.user.name")
    private String testUsername;

    @Value("test.user.password")
    private String testUserPass;

    @Autowired
    private ObjectMapper objectMapper;

    protected MockHttpServletRequestBuilder mockGetRequest(String url) throws Exception {
        return get(url).header(HttpHeaders.AUTHORIZATION, getAuthCode());
    }

    protected MockHttpServletRequestBuilder mockPostRequest(String url, Object content) throws Exception {
        return post(url).header(HttpHeaders.AUTHORIZATION, getAuthCode())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).content(toJson(content));
    }

    protected String getAuthCode() {
        return "Basic " + Base64Utils.encodeToString("user:password".getBytes());
    }

    protected String toJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }
}
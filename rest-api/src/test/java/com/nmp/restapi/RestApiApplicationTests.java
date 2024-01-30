package com.nmp.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmp.restapi.constants.SecurityConstants;
import com.nmp.restapi.dto.Contact;
import com.nmp.restapi.service.IContactService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiApplicationTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    IContactService contactService;
    @Autowired
    ObjectMapper objectMapper;
    static String token;

    private Contact[] contacts = new Contact[]{
            new Contact("1", "john", "12345"),
            new Contact("2", "emma", "6789"),
            new Contact("3", "dan", "25678"),
    };

    @Test
    void contextLoads() {
    }

    @BeforeEach
    void setUp() throws Exception {
        for (Contact contact : contacts) {
            contactService.saveContact(contact);
        }
        RequestBuilder register = MockMvcRequestBuilders.post("/user/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"user\",\"password\":\"password\"}");
        mockMvc.perform(register);
        RequestBuilder user = MockMvcRequestBuilders.post("/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"user\",\"password\":\"password\"}");
        token = mockMvc.perform(user).andReturn().getResponse().getHeader(SecurityConstants.AUTHORIZATION);
    }

    @AfterEach
    void tearDown() {
        contactService.getContacts().clear();
    }

    @Test
    void getContact() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/contact/1")
                .header(SecurityConstants.AUTHORIZATION, token);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(contacts[0].getName()))
                .andExpect(jsonPath("$.phoneNumber").value(contacts[0].getPhoneNumber()));
    }

    @Test
    void getContacts() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/contact/all")
                .header(SecurityConstants.AUTHORIZATION, token);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(contacts.length))
                .andExpect(jsonPath("$.[?(@.id == \"1\" && @.name == \"john\")]").exists());
    }

    @Test
    void saveContact() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/contact")
                .header(SecurityConstants.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Contact("4", "mike", "9876")));
        mockMvc.perform(request)
                .andExpect(status().isCreated());
    }

    @Test
    void invalidContact() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/contact")
                .header(SecurityConstants.AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Contact("4", "   ", "   ")));
        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void contactNotFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/contact/4")
                .header(SecurityConstants.AUTHORIZATION, token);
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }
}

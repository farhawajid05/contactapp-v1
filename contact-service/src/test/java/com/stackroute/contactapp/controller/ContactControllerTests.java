package com.stackroute.contactapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.contactapp.exceptions.ContactExistsException;
import com.stackroute.contactapp.model.Contact;
import com.stackroute.contactapp.service.ContactServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ContactController.class)
public class ContactControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactServiceImpl service;

    @Autowired
    private ObjectMapper mapper;

    private Contact contact;

    @BeforeEach
    public void setUp() {
        contact = new Contact("1234", "testname", "mail@test.com", "999111", "friends");
    }

    @Test
    public void givenContactJsonWhenContactAddedThenReturnCreatedStatus() throws Exception {
        //configure mock service call
        when(service.addContact(any(Contact.class))).thenReturn(contact);

        //Send MockRequest and Verify the result
        mockMvc.perform(
                post("/api/v1/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(contact)))
                        .andExpect(status().isCreated());

        verify(service).addContact(any(Contact.class));

    }
}
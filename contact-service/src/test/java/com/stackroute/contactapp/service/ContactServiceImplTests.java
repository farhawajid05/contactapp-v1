package com.stackroute.contactapp.service;

import com.stackroute.contactapp.exceptions.ContactNotFoundException;
import com.stackroute.contactapp.model.Contact;
import com.stackroute.contactapp.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContactServiceImplTests {

    @Mock
    private ContactRepository repository;

    @InjectMocks
    private ContactServiceImpl service;

    private Contact contact;

    @BeforeEach
    public void setUp() {
        contact = new Contact("1234", "testname", "mail@test.com", "999111", "friends");
    }

    @Test
    public void givenIdWhenContactExistsThenReturnContactObject() throws ContactNotFoundException {
        when(repository.findById(anyString())).thenReturn(Optional.of(contact));
        final Contact returnedContact = service.getContact("1234");
        assertEquals(contact, returnedContact);
        verify(repository,times(1)).findById(anyString());
    }

    @Test
//    @Disabled
    public void givenIdWhenContactDoesNOtExistsThenThrowException() throws ContactNotFoundException {
        when(repository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(ContactNotFoundException.class, () ->  service.getContact("1234"));
        verify(repository,times(1)).findById(anyString());
    }
}
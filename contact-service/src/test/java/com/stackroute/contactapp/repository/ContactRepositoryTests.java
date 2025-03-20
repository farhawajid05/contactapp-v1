package com.stackroute.contactapp.repository;

import com.stackroute.contactapp.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class ContactRepositoryTests {

    public static final String MAIL_TEST_COM = "mail@test.com";
    private ContactRepository repository;
    private Contact contact;

    @Autowired
    public ContactRepositoryTests(ContactRepository repository) {
        this.repository = repository;
    }

    @BeforeEach
    public void setUp() {
        contact = new Contact(null, "testname", "mail@test.com", "999111", "friends");
        this.repository.save(contact);
    }

    @Test
    public void givenContactEmailWhenContactExistsThenReturnOptional() {
        final Optional<Contact> optionalContact = this.repository.findByEmail(MAIL_TEST_COM);
        assertTrue(optionalContact.isPresent(), "Contact with email should exist");
        final Contact contact = optionalContact.get();
        assertEquals("testname", contact.getName());
    }

    @Test
    public void givenContactEmailWhenContactDoesNotExistsThenReturnEmptyOptional() {
        final Optional<Contact> optionalContact = this.repository.findByEmail("dummymail@test.com");
        assertTrue(optionalContact.isEmpty(), "Contact with email should not exist");
   }

}
package com.stackroute.contactapp.service;

import com.stackroute.contactapp.exceptions.ContactExistsException;
import com.stackroute.contactapp.exceptions.ContactNotFoundException;
import com.stackroute.contactapp.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Contact getContact(String contactId) throws ContactNotFoundException;
    Contact addContact(Contact contact) throws ContactExistsException;
    Optional<List<Contact>> getContacts();

}

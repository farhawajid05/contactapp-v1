package com.stackroute.contactapp.service;

import com.stackroute.contactapp.exceptions.ContactExistsException;
import com.stackroute.contactapp.exceptions.ContactNotFoundException;
import com.stackroute.contactapp.model.Contact;
import com.stackroute.contactapp.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository repository;

    @Autowired
    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact getContact(String contactId) throws ContactNotFoundException {
        final Optional<Contact> foundContact = this.repository.findById(contactId);
        if (foundContact.isPresent()) {
            return foundContact.get();
        } else {
            throw new ContactNotFoundException("Contact not found");
        }

    }

    @Override
    public Contact addContact(Contact contact) throws ContactExistsException {
        if(this.repository.findByEmail(contact.getEmail()).isPresent()){
            throw new ContactExistsException("Contact already exists");
        }
        return this.repository.save(contact);
    }

    @Override
    public Optional<List<Contact>> getContacts() {
        final List<Contact> allContacts = this.repository.findAll();
        if (allContacts.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(allContacts);
        }

    }
}

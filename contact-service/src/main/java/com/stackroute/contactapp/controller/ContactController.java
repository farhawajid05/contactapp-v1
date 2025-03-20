package com.stackroute.contactapp.controller;


import com.stackroute.contactapp.exceptions.ContactExistsException;
import com.stackroute.contactapp.exceptions.ContactNotFoundException;
import com.stackroute.contactapp.model.Contact;
import com.stackroute.contactapp.service.ContactService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ContactController {
    //contacts
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("welcome")
    public String welcome(){
        return "Welcome to the Contacts API";
    }

    // api/v1/contacts/102
    @GetMapping("/contacts/{contactId}")
    @ApiOperation("Get a contact for a given contact id")
    public ResponseEntity<?> getContact(@PathVariable("contactId")  String contactId) throws ContactNotFoundException {
        return new ResponseEntity<>(this.contactService.getContact(contactId), HttpStatus.OK);
    }

    // /contacts  GET   json

    @GetMapping("contacts")
    public List<Contact> getContacts(){
        final Optional<List<Contact>> contacts = this.contactService.getContacts();
        if(contacts.isPresent()){
            return contacts.get();
        }else{
            return null;
        }
    }


    //POST /contacts
    @PostMapping("contacts")
    public ResponseEntity<?> addContact(@RequestBody Contact contact) throws ContactExistsException {
        return new ResponseEntity<>(this.contactService.addContact(contact), HttpStatus.CREATED);
    }

}

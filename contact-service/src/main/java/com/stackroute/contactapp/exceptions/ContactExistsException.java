package com.stackroute.contactapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Contact already exists")
public class ContactExistsException extends Exception {
    public ContactExistsException(String message) {
        super(message);
    }
}

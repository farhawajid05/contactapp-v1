package com.stackroute.userservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User Already exists")
public class UserExistsException extends Exception {
    public UserExistsException(String message) {
        super(message);
    }
}

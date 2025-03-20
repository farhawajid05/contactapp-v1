package com.stackroute.contactapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ContactExistsException.class)
    public ResponseEntity<?>  handleContactExistsException(ContactExistsException exception){
            return new ResponseEntity<>("Contact with the email already exists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<?>  handleContactNotFoundException(ContactNotFoundException exception){
        return new ResponseEntity<>("Contact does not exist with given id", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidHeaderException.class)
    public ResponseEntity<?>  handleContactNotFoundException(InvalidTokenException exception){
        return new ResponseEntity<>("Invalid Header", HttpStatus.BAD_REQUEST);
    }
}

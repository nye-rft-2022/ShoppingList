package com.epam.example.shoppinglist.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to represent when user already exists.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email address already in use.")
public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}

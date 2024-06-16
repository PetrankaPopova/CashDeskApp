package com.example.cashdeskapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an invalid banknote quantity is detected.
 * This exception results in a response with a 400 Bad Request status and a specific error message.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Banknotes quantity is invalid!")
public class InvalidBanknoteQuantityException extends RuntimeException {

    /**
     * Constructs a new InvalidBanknoteQuantityException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidBanknoteQuantityException(String message) {
        super(message);
    }
}
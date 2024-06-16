package com.example.cashdeskapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an invalid banknote denomination is detected.
 * This exception results in a response with a 400 Bad Request status and a specific error message.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Banknote denomination is invalid!")
public class InvalidBanknoteDenominationException extends RuntimeException {

    /**
     * Constructs a new InvalidBanknoteDenominationException with the specified detail message.
     *
     * @param message the detail message.
     */
    public InvalidBanknoteDenominationException(String message) {
        super(message);
    }
}

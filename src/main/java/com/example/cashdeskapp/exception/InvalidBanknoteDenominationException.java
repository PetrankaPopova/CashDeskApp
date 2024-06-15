package com.example.cashdeskapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Banknote denomination is invalid!")
public class InvalidBanknoteDenominationException extends RuntimeException {

    public InvalidBanknoteDenominationException(String message) {
        super(message);
    }
}


package com.example.cashdeskapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Banknotes quantity is invalid!")
public class InvalidBanknoteQuantityException extends RuntimeException{

    public InvalidBanknoteQuantityException(String message) {
        super(message);
    }
}

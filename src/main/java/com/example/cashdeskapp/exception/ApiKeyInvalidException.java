package com.example.cashdeskapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Api key is invalid!")
public class ApiKeyInvalidException extends RuntimeException {

    public ApiKeyInvalidException(String message) {
        super(message);
    }
}

package com.example.cashdeskapp.exceptionHandler;




import com.example.cashdeskapp.exception.ApiKeyInvalidException;
import com.example.cashdeskapp.exception.InvalidBanknoteDenominationException;
import com.example.cashdeskapp.exception.InvalidBanknoteQuantityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class CashDeskControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException validationException) {
        List<String> validationErrors = new ArrayList<>();
        for (FieldError fieldError : validationException.getBindingResult().getFieldErrors()) {
            validationErrors.add(fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException messageNotReadableException) {
        String errorMessage = "Invalid JSON format. Please check your input.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBanknoteQuantityException.class)
    public ResponseEntity<String> handleInvalidBanknoteQuantityException(InvalidBanknoteQuantityException quantityException) {
        return new ResponseEntity<>(quantityException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBanknoteDenominationException.class)
    public ResponseEntity<String> handleInvalidBanknoteDenominationException(InvalidBanknoteDenominationException denominationException) {
        return new ResponseEntity<>(denominationException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiKeyInvalidException.class)
    public ResponseEntity<String> handleInvalidApiKeyException(ApiKeyInvalidException apiKeyInvalidException) {
        return new ResponseEntity<>(apiKeyInvalidException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
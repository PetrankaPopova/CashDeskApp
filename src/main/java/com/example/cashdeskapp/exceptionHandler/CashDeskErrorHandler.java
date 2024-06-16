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
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;


/**
 * Global exception handler for the Cash Desk application.
 * Handles specific exceptions and maps them to appropriate HTTP status codes and response bodies.
 */
@ControllerAdvice
public class CashDeskErrorHandler {

    /**
     * Handles validation exceptions thrown during method argument validation.
     * Maps field errors to a list of error messages and returns them with a status of 400 Bad Request.
     *
     * @param ex the MethodArgumentNotValidException instance
     * @return ResponseEntity containing a list of validation error messages and a status of 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles HTTP message not readable exceptions caused by invalid JSON format.
     * Returns a generic error message indicating the issue with JSON format and a status of 400 Bad Request.
     *
     * @param ex the HttpMessageNotReadableException instance
     * @return ResponseEntity with a generic error message and a status of 400 Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Invalid JSON format. Please check your input.");
    }

    /**
     * Handles IllegalArgumentException and related runtime exceptions.
     * Returns the exception message in the response body with a status of 400 Bad Request.
     *
     * @param ex the RuntimeException instance
     * @return ResponseEntity with the exception message and a status of 400 Bad Request
     */
    @ExceptionHandler({IllegalArgumentException.class, InvalidBanknoteQuantityException.class,
            InvalidBanknoteDenominationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Handles ApiKeyInvalidException thrown when the API key is invalid or missing.
     * Returns the exception message in the response body with a status of 401 Unauthorized.
     *
     * @param ex the ApiKeyInvalidException instance
     * @return ResponseEntity with the exception message and a status of 401 Unauthorized
     */
    @ExceptionHandler(ApiKeyInvalidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleInvalidApiKeyException(ApiKeyInvalidException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
package com.example.cashdeskapp.controller;


import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.dto.CashOperationDTO;
import com.example.cashdeskapp.exception.ApiKeyInvalidException;
import com.example.cashdeskapp.service.impl.CashOperationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
/**
 * REST controller for handling cash operations and retrieving cash balances.
 */
@RestController
@RequestMapping("/api/v1")
public class CashOperationController {

    private final CashOperationServiceImpl operationService;

    /**
     * Constructs a new CashOperationController with the specified operation service.
     *
     * @param operationServiceImpl the implementation of the cash operation service
     */
    public CashOperationController(CashOperationServiceImpl operationServiceImpl) {
        this.operationService = operationServiceImpl;
    }

    /**
     * Processes a cash operation (deposit or withdrawal).
     *
     * @param apiKey the API key provided in the request header for authentication
     * @param cashOperation the cash operation details
     * @return the processed cash operation details
     * @throws IOException if there is an error during processing
     * @throws ApiKeyInvalidException if the provided API key is invalid
     */
    @PostMapping("/cash-operation")
    public ResponseEntity<CashOperationDTO> handleCashOperation(
            @RequestHeader("FIB-X-AUTH") String apiKey,
            @Valid @RequestBody CashOperationDTO cashOperation) throws IOException {
        if (!operationService.isApiKeyValid(apiKey)) {
            throw new ApiKeyInvalidException("API key is invalid.");
        }

        CashOperationDTO dto = operationService.processCashOperation(cashOperation);
        return ResponseEntity.ok(dto);
    }

    /**
     * Retrieves the current cash balance.
     *
     * @param apiKey the API key provided in the request header for authentication
     * @return the current cash balance
     * @throws IOException if there is an error during retrieval
     * @throws ApiKeyInvalidException if the provided API key is invalid
     */
    @GetMapping("/cash-balance")
    public ResponseEntity<CashBalanceDTO> fetchCashBalance(@RequestHeader("FIB-X-AUTH") String apiKey) throws IOException {
        if (!operationService.isApiKeyValid(apiKey)) {
            throw new ApiKeyInvalidException("API key is invalid.");
        }

        CashBalanceDTO cashBalanceDTO = operationService.fetchCashBalance();
        return ResponseEntity.ok(cashBalanceDTO);
    }
}
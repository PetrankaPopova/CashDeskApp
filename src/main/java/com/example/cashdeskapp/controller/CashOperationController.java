package com.example.cashdeskapp.controller;


import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.dto.CashOperationDTO;
import com.example.cashdeskapp.exception.ApiKeyInvalidException;
import com.example.cashdeskapp.service.impl.CashOperationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class CashOperationController {

    private final CashOperationServiceImpl operationService;

    public CashOperationController(CashOperationServiceImpl operationServiceImpl) {
        this.operationService = operationServiceImpl;
    }

    @PostMapping("/cash-operation")
    public ResponseEntity<CashOperationDTO> cashOperation(@RequestHeader("FIB-X-AUTH") String apiKey, @Valid @RequestBody CashOperationDTO cashOperation) throws IOException {
        if (!operationService.isApiKeyValid(apiKey)) {
            throw new ApiKeyInvalidException("API key is invalid.");
        }

        CashOperationDTO dto = operationService.processCashOperation(cashOperation);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/cash-balance")
    public ResponseEntity<CashBalanceDTO> getCashBalance(@RequestHeader("FIB-X-AUTH") String apiKey) throws IOException {
        if (!operationService.isApiKeyValid(apiKey)) {
            throw new ApiKeyInvalidException("API key is invalid.");
        }

        CashBalanceDTO cashBalanceDTO = operationService.fetchCashBalance();
        return ResponseEntity.ok(cashBalanceDTO);
    }
}

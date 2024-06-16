package com.example.cashdeskapp.service;


import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.dto.CashOperationDTO;

import java.io.IOException;

/**
 * This interface defines operations related to cash desk operations and balances.
 */
public interface OperationService {

    /**
     * Validates the provided API key.
     *
     * @param apiKey The API key to validate.
     * @return true if the API key is valid, false otherwise.
     */
    boolean isApiKeyValid(String apiKey);

    /**
     * Fetches the current cash balance from storage.
     *
     * @return A CashBalanceDTO representing the current cash balance.
     * @throws IOException If an I/O exception occurs during the operation.
     */
    CashBalanceDTO fetchCashBalance() throws IOException;

    /**
     * Processes a cash operation based on the provided CashOperationDTO.
     *
     * @param cashOperationDTO The DTO containing details of the cash operation to process.
     * @return A CashOperationDTO representing the processed cash operation.
     * @throws IOException If an I/O exception occurs during the operation.
     */
    CashOperationDTO processCashOperation(CashOperationDTO cashOperationDTO) throws IOException;
}

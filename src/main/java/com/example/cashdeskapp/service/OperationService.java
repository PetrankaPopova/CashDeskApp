package com.example.cashdeskapp.service;


import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.dto.CashOperationDTO;

import java.io.IOException;

public interface OperationService {

     boolean isApiKeyValid(String apiKey);

    CashBalanceDTO fetchCashBalance() throws IOException;

    CashOperationDTO processCashOperation(CashOperationDTO cashOperationDTO) throws IOException;
}

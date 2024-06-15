package com.example.cashdeskapp.service;


import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.dto.CashOperationDTO;

import java.io.IOException;

public interface OperationService {

     boolean isApiKeyValid(String apiKey);

    CashBalanceDTO retrieveCashBalance() throws IOException;

    public CashOperationDTO performCashOperation(CashOperationDTO cashOperationDTO) throws IOException;
}

package com.example.cashdeskapp.entity;

import com.example.cashdeskapp.CashOperationType;
import com.example.cashdeskapp.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
@Data
@AllArgsConstructor
public class CashOperation {

    private CashOperationType type;
    private Currency currency;
    private BigDecimal amount;
    private Map<Integer, Integer> denomination;
}

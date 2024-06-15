package com.example.cashdeskapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
@Data
@AllArgsConstructor
public class CashBalanceCurrencyDTO {

    private BigDecimal totalBalance;
    private Map<Integer, Integer> denomination;

    public CashBalanceCurrencyDTO() {

    }
}

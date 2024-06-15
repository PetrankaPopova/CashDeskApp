package com.example.cashdeskapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
@Data
@AllArgsConstructor
public class CashBalanceCurrency {

    private BigDecimal totalBalance;
    private Map<Integer, Integer> denomination;

    public CashBalanceCurrency() {
        this.totalBalance = BigDecimal.ZERO;
        this.denomination = new HashMap<>();
    }
}

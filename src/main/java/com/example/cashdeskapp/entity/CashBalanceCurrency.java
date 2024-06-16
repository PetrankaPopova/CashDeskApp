package com.example.cashdeskapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
/**
 * Represents the cash balance for a specific currency, including the total balance and the breakdown of banknotes by denomination.
 */
@Data
@AllArgsConstructor
public class CashBalanceCurrency {

    /**
     * The total balance in the specified currency.
     */
    private BigDecimal totalBalance;

    /**
     * A map representing the denomination of banknotes and their respective quantities.
     * The key is the denomination (e.g., 10, 50), and the value is the quantity of banknotes.
     */
    private Map<Integer, Integer> denomination;

    /**
     * Default constructor for CashBalanceCurrency.
     * Initializes the total balance to zero and the denomination map to an empty map.
     */
    public CashBalanceCurrency() {
        this.totalBalance = BigDecimal.ZERO;
        this.denomination = new HashMap<>();
    }
}
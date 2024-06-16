package com.example.cashdeskapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
/**
 * Data Transfer Object representing the balance and denomination of a specific currency.
 */
@Data
@AllArgsConstructor
public class CashBalanceCurrencyDTO {

    /**
     * The total balance of the currency.
     */
    private BigDecimal totalBalance;

    /**
     * The denomination of banknotes, where the key is the denomination value
     * and the value is the count of banknotes of that denomination.
     */
    private Map<Integer, Integer> denomination;

    /**
     * Default constructor for CashBalanceCurrencyDTO.
     */
    public CashBalanceCurrencyDTO() {
        // Default constructor needed for deserialization and other use cases.
    }
}
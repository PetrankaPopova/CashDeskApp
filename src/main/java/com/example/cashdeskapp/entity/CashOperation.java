package com.example.cashdeskapp.entity;

import com.example.cashdeskapp.CashOperationType;
import com.example.cashdeskapp.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
/**
 * Represents a cash operation, including the type of operation, the currency involved,
 * the amount of money, and the breakdown of the banknotes by denomination.
 */
@Data
@AllArgsConstructor
public class CashOperation {

    /**
     * The type of cash operation (e.g., DEPOSIT, WITHDRAWAL).
     */
    private CashOperationType type;

    /**
     * The currency in which the cash operation is performed.
     */
    private Currency currency;

    /**
     * The amount of money involved in the cash operation.
     */
    private BigDecimal amount;

    /**
     * A map representing the denomination of banknotes and their respective quantities.
     * The key is the denomination (e.g., 10, 50), and the value is the quantity of banknotes.
     */
    private Map<Integer, Integer> denomination;
}
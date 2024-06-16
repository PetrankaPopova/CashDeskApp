package com.example.cashdeskapp.entity;

import java.util.HashMap;
import java.util.Map;

import com.example.cashdeskapp.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Represents the cash balance for multiple currencies, including the breakdown of banknotes by denomination.
 */
@Data
@AllArgsConstructor
public class CashBalance {

    /**
     * A map representing the cash balance for each currency.
     * The key is the currency, and the value is the corresponding cash balance details.
     */
    private Map<Currency, CashBalanceCurrency> balance;

    /**
     * Default constructor for CashBalance.
     * Initializes the balance map.
     */
    public CashBalance() {
        balance = new HashMap<>();
    }

    /**
     * Sets the cash balance for multiple currencies.
     *
     * @param balance A map representing the cash balance for each currency.
     *                The key is the currency, and the value is the corresponding cash balance details.
     * @return The updated CashBalance object.
     */
    public CashBalance setBalance(Map<Currency, CashBalanceCurrency> balance) {
        this.balance = balance;
        return this;
    }
}
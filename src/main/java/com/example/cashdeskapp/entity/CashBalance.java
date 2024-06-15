package com.example.cashdeskapp.entity;

import java.util.HashMap;
import java.util.Map;

import com.example.cashdeskapp.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CashBalance {

    private Map<Currency, CashBalanceCurrency> balance;

    public CashBalance() {
        balance = new HashMap<>();
    }


    public CashBalance setBalance(Map<Currency, CashBalanceCurrency> balance) {
        this.balance = balance;
        return this;
    }
}

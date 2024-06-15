package com.example.cashdeskapp.mapper.impl;

import com.example.cashdeskapp.Currency;
import com.example.cashdeskapp.dto.CashBalanceCurrencyDTO;
import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.entity.CashBalance;
import com.example.cashdeskapp.entity.CashBalanceCurrency;
import com.example.cashdeskapp.mapper.CashBalanceMapper;

import java.util.HashMap;
import java.util.Map;

public class CashBalanceMapperImpl implements CashBalanceMapper {
    @Override
    public CashBalance toCashBalance(CashBalanceDTO cashBalanceDTO) {
        if (cashBalanceDTO == null) {
            return null;
        }

        CashBalance cashBalance = new CashBalance();
        return cashBalance;
    }

    @Override
    public CashBalanceDTO toCashBalanceDTO(CashBalance cashBalance) {
        if (cashBalance == null) {
            return null;
        }

        CashBalanceDTO cashBalanceDTO = new CashBalanceDTO();
        Map<Currency, CashBalanceCurrencyDTO> balanceDTO = new HashMap<>();
        for (Map.Entry<Currency, CashBalanceCurrency> entry : cashBalance.getBalance().entrySet()) {
            Currency currency = entry.getKey();
            CashBalanceCurrency cashBalanceCurrency = entry.getValue();
            CashBalanceCurrencyDTO currencyDTO = new CashBalanceCurrencyDTO();
            currencyDTO.setTotalBalance(cashBalanceCurrency.getTotalBalance());
            balanceDTO.put(currency, currencyDTO);
        }

        cashBalanceDTO.setBalance(balanceDTO);
        return cashBalanceDTO;
    }
}


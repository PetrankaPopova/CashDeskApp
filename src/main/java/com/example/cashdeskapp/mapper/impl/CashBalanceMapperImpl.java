package com.example.cashdeskapp.mapper.impl;

import com.example.cashdeskapp.Currency;
import com.example.cashdeskapp.dto.CashBalanceCurrencyDTO;
import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.entity.CashBalance;
import com.example.cashdeskapp.entity.CashBalanceCurrency;
import com.example.cashdeskapp.mapper.CashBalanceMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link CashBalanceMapper} interface that provides methods to convert
 * between {@link CashBalanceDTO} and {@link CashBalance} objects.
 */
public class CashBalanceMapperImpl implements CashBalanceMapper {

    /**
     * Converts a {@link CashBalanceDTO} object to a {@link CashBalance} object.
     *
     * @param cashBalanceDTO The {@link CashBalanceDTO} object to convert.
     * @return The converted {@link CashBalance} object, or {@code null} if {@code cashBalanceDTO} is {@code null}.
     */
    @Override
    public CashBalance toCashBalance(CashBalanceDTO cashBalanceDTO) {
        if (cashBalanceDTO == null) {
            return null;
        }

        CashBalance cashBalance = new CashBalance();
        // Additional mapping logic can be added here if needed
        return cashBalance;
    }

    /**
     * Converts a {@link CashBalance} object to a {@link CashBalanceDTO} object.
     *
     * @param cashBalance The {@link CashBalance} object to convert.
     * @return The converted {@link CashBalanceDTO} object, or {@code null} if {@code cashBalance} is {@code null}.
     */
    @Override
    public CashBalanceDTO toCashBalanceDTO(CashBalance cashBalance) {
        if (cashBalance == null) {
            return null;
        }

        CashBalanceDTO cashBalanceDTO = new CashBalanceDTO();
        Map<Currency, CashBalanceCurrencyDTO> balanceDTO = new HashMap<>();

        // Iterate over the balance map in CashBalance and convert each entry
        for (Map.Entry<Currency, CashBalanceCurrency> entry : cashBalance.getBalance().entrySet()) {
            Currency currency = entry.getKey();
            CashBalanceCurrency cashBalanceCurrency = entry.getValue();

            // Create CashBalanceCurrencyDTO and set its properties
            CashBalanceCurrencyDTO currencyDTO = new CashBalanceCurrencyDTO();
            currencyDTO.setTotalBalance(cashBalanceCurrency.getTotalBalance());
            currencyDTO.setDenomination(new HashMap<>(cashBalanceCurrency.getDenomination()));

            // Put the currency and corresponding DTO into the balanceDTO map
            balanceDTO.put(currency, currencyDTO);
        }

        // Set the balanceDTO map in the CashBalanceDTO object
        cashBalanceDTO.setBalance(balanceDTO);
        return cashBalanceDTO;
    }
}

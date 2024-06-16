package com.example.cashdeskapp.mapper;

import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.entity.CashBalance;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper interface for converting between {@link CashBalanceDTO} and {@link CashBalance}.
 * Uses MapStruct to automatically generate implementation based on mappings defined in this interface.
 */
@Component
@Mapper(componentModel = "spring")
public interface CashBalanceMapper {

    /**
     * Converts a {@link CashBalanceDTO} object to a {@link CashBalance} object.
     *
     * @param cashBalanceDTO The {@link CashBalanceDTO} object to convert.
     * @return The converted {@link CashBalance} object.
     */
    CashBalance toCashBalance(CashBalanceDTO cashBalanceDTO);

    /**
     * Converts a {@link CashBalance} object to a {@link CashBalanceDTO} object.
     *
     * @param cashBalance The {@link CashBalance} object to convert.
     * @return The converted {@link CashBalanceDTO} object.
     */
    CashBalanceDTO toCashBalanceDTO(CashBalance cashBalance);
}

package com.example.cashdeskapp.mapper;


import com.example.cashdeskapp.dto.CashOperationDTO;
import com.example.cashdeskapp.entity.CashOperation;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@link CashOperationDTO} and {@link CashOperation}.
 * Uses MapStruct to automatically generate implementation based on mappings defined in this interface.
 */
@Mapper(componentModel = "spring")
public interface CashOperationMapper {

    /**
     * Converts a {@link CashOperationDTO} object to a {@link CashOperation} object.
     *
     * @param cashOperationDTO The {@link CashOperationDTO} object to convert.
     * @return The converted {@link CashOperation} object.
     */
    CashOperation toCashOperation(CashOperationDTO cashOperationDTO);

    /**
     * Converts a {@link CashOperation} object to a {@link CashOperationDTO} object.
     *
     * @param cashOperation The {@link CashOperation} object to convert.
     * @return The converted {@link CashOperationDTO} object.
     */
    CashOperationDTO toCashOperationDTO(CashOperation cashOperation);
}
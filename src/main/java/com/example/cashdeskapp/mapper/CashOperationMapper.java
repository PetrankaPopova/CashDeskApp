package com.example.cashdeskapp.mapper;


import com.example.cashdeskapp.dto.CashOperationDTO;
import com.example.cashdeskapp.entity.CashOperation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CashOperationMapper {

    CashOperation toCashOperation(CashOperationDTO cashOperationDTO);

    CashOperationDTO toCashOperationDTO(CashOperation cashOperation);
}

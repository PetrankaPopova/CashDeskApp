package com.example.cashdeskapp.mapper;

import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.entity.CashBalance;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CashBalanceMapper {
    CashBalanceDTO toCashBalanceDTO(CashBalance cashBalance);
}

package com.example.cashdeskapp.dto;


import com.example.cashdeskapp.CashOperationType;
import com.example.cashdeskapp.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
@Data
@AllArgsConstructor
public class CashOperationDTO {

    private LocalDateTime localDateTime;
    @NotNull(message = "Cash operation type can not be null.")
    private CashOperationType type;
    @NotNull(message = "Currency can not be null.")
    private Currency currency;
    @NotNull(message = "Amount must not be null.")
    @Positive(message = "Amount must be positive.")
    private BigDecimal amount;
    private Map<Integer, Integer> denomination;


}

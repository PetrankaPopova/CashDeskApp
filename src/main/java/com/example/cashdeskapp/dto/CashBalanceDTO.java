package com.example.cashdeskapp.dto;




import com.example.cashdeskapp.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@AllArgsConstructor
public class CashBalanceDTO {

    private LocalDateTime localDateTime;
    private Map<Currency, CashBalanceCurrencyDTO> balance;

    public CashBalanceDTO() {

    }
}

package com.example.cashdeskapp.dto;




import com.example.cashdeskapp.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
/**
 * Data Transfer Object representing the overall cash balance,
 * including the time of the balance and the breakdown by currency.
 */
@Data
@AllArgsConstructor
public class CashBalanceDTO {

    /**
     * The date and time when the cash balance was recorded.
     */
    private LocalDateTime localDateTime;

    /**
     * A map containing the balance for each currency,
     * where the key is the currency and the value is the currency balance.
     */
    private Map<Currency, CashBalanceCurrencyDTO> balance;

    /**
     * Default constructor for CashBalanceDTO.
     */
    public CashBalanceDTO() {
        // Default constructor needed for deserialization and other use cases.
    }
}

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
/**
 * Data Transfer Object representing a cash operation, including its details such as
 * the type of operation, the currency involved, the amount, and the denomination breakdown.
 */
@Data
@AllArgsConstructor
public class CashOperationDTO {

    /**
     * The date and time when the cash operation was performed.
     */
    private LocalDateTime localDateTime;

    /**
     * The type of cash operation (e.g., deposit, withdrawal).
     * This field is mandatory and must not be null.
     */
    @NotNull(message = "Cash operation type cannot be null.")
    private CashOperationType type;

    /**
     * The currency involved in the cash operation.
     * This field is mandatory and must not be null.
     */
    @NotNull(message = "Currency cannot be null.")
    private Currency currency;

    /**
     * The amount involved in the cash operation.
     * This field is mandatory, must not be null, and must be positive.
     */
    @NotNull(message = "Amount must not be null.")
    @Positive(message = "Amount must be positive.")
    private BigDecimal amount;

    /**
     * The breakdown of banknotes by denomination involved in the cash operation.
     * This is an optional field and may be null.
     */
    private Map<Integer, Integer> denomination;

    /**
     * Default constructor for CashOperationDTO.
     */
    public CashOperationDTO() {
        // Default constructor needed for deserialization and other use cases.
    }
}
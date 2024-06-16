package com.example.cashdeskapp.service.impl;


import com.example.cashdeskapp.CashOperationType;
import com.example.cashdeskapp.Currency;
import com.example.cashdeskapp.configuration.BanknotesDenominationsConfiguration;
import com.example.cashdeskapp.dto.CashBalanceCurrencyDTO;
import com.example.cashdeskapp.dto.CashBalanceDTO;
import com.example.cashdeskapp.dto.CashOperationDTO;
import com.example.cashdeskapp.entity.CashBalance;
import com.example.cashdeskapp.entity.CashBalanceCurrency;
import com.example.cashdeskapp.exception.InvalidBanknoteQuantityException;
import com.example.cashdeskapp.mapper.CashBalanceMapper;
import com.example.cashdeskapp.service.OperationService;
import com.example.cashdeskapp.util.FileUtilsHelper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Service implementation for managing cash operations.
 * Implements {@link OperationService}.
 */
@Service
public class CashOperationServiceImpl implements OperationService {

    private final String apiKey;
    private final CashBalanceMapper cashBalanceMapper;
    private final BanknotesDenominationsConfiguration denominations;
    private static final int[] ALLOWED_BGN_DENOMINATIONS = {5, 10, 20, 50, 100};
    private static final int[] ALLOWED_EUR_DENOMINATIONS = {5, 10, 20, 50, 100, 200, 500};

    /**
     * Constructor to initialize CashOperationServiceImpl.
     *
     * @param cashBalanceMapper The CashBalanceMapper for mapping CashBalance and CashBalanceDTO.
     * @param denominations     The BanknotesDenominationsConfiguration for allowed banknote denominations.
     * @param apiKey            The API key value injected from properties.
     */
    @Autowired
    public CashOperationServiceImpl(CashBalanceMapper cashBalanceMapper,
                                    BanknotesDenominationsConfiguration denominations,
                                    @Value("${api.key}") String apiKey) {
        this.cashBalanceMapper = cashBalanceMapper;
        this.denominations = denominations;
        this.apiKey = apiKey;
    }

    /**
     * Validates the API key.
     *
     * @param apiKey The API key to validate.
     * @return true if the API key is valid, false otherwise.
     */
    @Override
    public boolean isApiKeyValid(String apiKey) {
        return this.apiKey.equals(apiKey);
    }

    /**
     * Retrieves the current cash balance.
     *
     * @return The CashBalanceDTO representing the current cash balance.
     * @throws IOException if there's an error reading the cash balance.
     */
    @Override
    public CashBalanceDTO fetchCashBalance() throws IOException {
        CashBalanceDTO cashBalanceDTO = FileUtilsHelper.readLastBalance();
        return cashBalanceDTO;
    }

    /**
     * Processes a cash operation based on the provided CashOperationDTO.
     *
     * @param cashOperationDTO The CashOperationDTO containing the operation details.
     * @return The processed CashOperationDTO.
     * @throws IOException if there's an error recording the operation.
     */
    @Override
    public CashOperationDTO processCashOperation(CashOperationDTO cashOperationDTO) throws IOException {
        CashOperationType operation = cashOperationDTO.getType();

        CashBalanceDTO balanceDTO = fetchCashBalance();
        validateOperations(cashOperationDTO, balanceDTO);

        if (operation.equals(CashOperationType.WITHDRAWAL)) {
            withdrawCash(cashOperationDTO, balanceDTO);
        } else if (operation.equals(CashOperationType.DEPOSIT)) {
            depositCash(cashOperationDTO, balanceDTO);
        }

        FileUtilsHelper.recordCashBalance(balanceDTO);
        FileUtilsHelper.recordCashOperation(cashOperationDTO);

        return cashOperationDTO;
    }

    /**
     * Initializes the cash balance if the file is empty.
     *
     * @throws IOException if there's an error reading or writing the cash balance file.
     */
    @PostConstruct
    private void init() throws IOException {
        if (FileUtilsHelper.isCashBalanceFileEmpty()) {
            CashBalance initialBalance = new CashBalance().setBalance(createInitialBalances());
            FileUtilsHelper.recordCashBalance(cashBalanceMapper.toCashBalanceDTO(initialBalance));
        }
    }

    /**
     * Deposits cash into the balance based on the operation details.
     *
     * @param cashOperationDTO The CashOperationDTO containing deposit details.
     * @param balanceDTO       The current CashBalanceDTO.
     */
    private void depositCash(CashOperationDTO cashOperationDTO, CashBalanceDTO balanceDTO) {
        CashBalanceCurrencyDTO cashBalanceCurrency = balanceDTO.getBalance().get(cashOperationDTO.getCurrency());

        BigDecimal totalBalance = cashBalanceCurrency.getTotalBalance();
        for (Map.Entry<Integer, Integer> entry : cashOperationDTO.getDenomination().entrySet()) {
            int banknote = entry.getKey();
            int count = entry.getValue();

            cashBalanceCurrency.getDenomination().putIfAbsent(banknote, 0);
            cashBalanceCurrency.getDenomination().put(banknote, cashBalanceCurrency.getDenomination().get(banknote) + count);

            totalBalance = totalBalance.add(BigDecimal.valueOf(banknote).multiply(BigDecimal.valueOf(count)));
        }
        cashBalanceCurrency.setTotalBalance(totalBalance);
    }

    /**
     * Withdraws cash from the balance based on the operation details.
     *
     * @param cashOperationDTO The CashOperationDTO containing withdrawal details.
     * @param balanceDTO       The current CashBalanceDTO.
     */
    private void withdrawCash(CashOperationDTO cashOperationDTO, CashBalanceDTO balanceDTO) {
        CashBalanceCurrencyDTO cashBalanceCurrency = balanceDTO.getBalance().get(cashOperationDTO.getCurrency());
        BigDecimal totalBalance = cashBalanceCurrency.getTotalBalance();

        for (Map.Entry<Integer, Integer> entry : cashOperationDTO.getDenomination().entrySet()) {
            int banknote = entry.getKey();
            int count = entry.getValue();

            cashBalanceCurrency.getDenomination().put(banknote, cashBalanceCurrency.getDenomination().get(banknote) - count);
            totalBalance = totalBalance.subtract(BigDecimal.valueOf(banknote).multiply(BigDecimal.valueOf(count)));
        }

        cashBalanceCurrency.setTotalBalance(totalBalance);
    }

    /**
     * Validates the operations based on the cash operation details.
     *
     * @param cashOperationDTO The CashOperationDTO containing operation details.
     * @param balanceDTO       The current CashBalanceDTO.
     */
    private void validateOperations(CashOperationDTO cashOperationDTO, CashBalanceDTO balanceDTO) {
        validateBanknotesDenomination(cashOperationDTO);
        checkNegativeBanknotesCount(cashOperationDTO);
        ensureFundsSufficiency(cashOperationDTO, balanceDTO);
    }

    /**
     * Ensures that there are sufficient funds for withdrawal operations.
     *
     * @param cashOperationDTO The CashOperationDTO containing withdrawal details.
     * @param balanceDTO       The current CashBalanceDTO.
     */
    private void ensureFundsSufficiency(CashOperationDTO cashOperationDTO, CashBalanceDTO balanceDTO) {
        if (cashOperationDTO.getType().equals(CashOperationType.WITHDRAWAL)) {
            Currency currency = cashOperationDTO.getCurrency();
            Map<Integer, Integer> currentOperationDenominations = cashOperationDTO.getDenomination();

            CashBalanceCurrencyDTO cashBalanceCurrency = balanceDTO.getBalance().get(currency);

            BigDecimal requestedWithdrawalAmount = cashOperationDTO.getAmount();
            BigDecimal banknotesAmount = BigDecimal.ZERO;
            for (Map.Entry<Integer, Integer> entry : currentOperationDenominations.entrySet()) {
                int banknote = entry.getKey();
                int count = entry.getValue();

                banknotesAmount = banknotesAmount.add(BigDecimal.valueOf(banknote).multiply(BigDecimal.valueOf(count)));

                Integer availableCount = cashBalanceCurrency.getDenomination().get(banknote);
                if (availableCount == null || availableCount < count) {
                    String message = "Insufficient quantity of banknote denomination: " + banknote +
                            " for currency: " + currency.name() + ". Requested: " + count + ", Available: " + (availableCount == null ? 0 : availableCount);
                    throw new InvalidBanknoteQuantityException(message);
                }
            }

            if (!requestedWithdrawalAmount.equals(banknotesAmount)) {
                String message = "Invalid withdrawal amount: Requested amount " + requestedWithdrawalAmount +
                        " does not match the total banknotes amount " + banknotesAmount;
                throw new IllegalArgumentException(message);
            }
        }
    }

    /**
     * Checks for negative banknotes count in the operation details.
     *
     * @param cashOperationDTO The CashOperationDTO containing operation details.
     */
    private void checkNegativeBanknotesCount(CashOperationDTO cashOperationDTO) {
        Map<Integer, Integer> currentOperationDenominations = cashOperationDTO.getDenomination();

        for (Integer count : currentOperationDenominations.values()) {
            if (count < 0) {
                throw new InvalidBanknoteQuantityException("Banknotes count must be greater than zero");
            }
        }
    }

    /**
     * Validates banknote denominations for the given operation.
     *
     * @param cashOperationDTO The CashOperationDTO containing operation details.
     */
    private void validateBanknotesDenomination(CashOperationDTO cashOperationDTO) {
        String currency = String.valueOf(cashOperationDTO.getCurrency());
        Map<Integer, Integer> currentOperationDenominations = cashOperationDTO.getDenomination();

        int[] allowedDenominations = null;
        if ("BGN".equals(currency)) {
            allowedDenominations = ALLOWED_BGN_DENOMINATIONS;
        } else if ("EUR".equals(currency)) {
            allowedDenominations = ALLOWED_EUR_DENOMINATIONS;
        } else {
            throw new IllegalArgumentException("Unsupported currency: " + currency);
        }

        for (int denomination : currentOperationDenominations.keySet()) {
            if (!isValidDenomination(denomination, allowedDenominations)) {
                throw new IllegalArgumentException("Invalid denomination for " + currency + ": " + denomination);
            }
        }
    }

    /**
     * Checks if the provided denomination is valid for the given currency.
     *
     * @param denomination         The denomination to check.
     * @param allowedDenominations The array of allowed denominations for the currency.
     * @return true if the denomination is valid, false otherwise.
     */
    private boolean isValidDenomination(int denomination, int[] allowedDenominations) {
        for (int allowed : allowedDenominations) {
            if (denomination == allowed) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates initial cash balances for supported currencies (BGN and EUR).
     *
     * @return A map containing initial CashBalanceCurrency objects for BGN and EUR.
     */
    private Map<Currency, CashBalanceCurrency> createInitialBalances() {
        Map<Currency, CashBalanceCurrency> balances = new HashMap<>();

        balances.put(Currency.BGN, createBGNBalance());
        balances.put(Currency.EUR, createEURBalance());

        return balances;
    }

    /**
     * Creates initial cash balance for Bulgarian Lev (BGN).
     *
     * @return A CashBalanceCurrency object representing the initial BGN balance.
     */
    private CashBalanceCurrency createBGNBalance() {
        CashBalanceCurrency bgnBalance = new CashBalanceCurrency();
        bgnBalance.setTotalBalance(BigDecimal.valueOf(1000));

        Map<Integer, Integer> bgnDenomination = new HashMap<>();
        bgnDenomination.put(10, 50);
        bgnDenomination.put(50, 10);

        bgnBalance.setDenomination(bgnDenomination);

        return bgnBalance;
    }

    /**
     * Creates initial cash balance for Euro (EUR).
     *
     * @return A CashBalanceCurrency object representing the initial EUR balance.
     */
    private CashBalanceCurrency createEURBalance() {
        CashBalanceCurrency eurBalance = new CashBalanceCurrency();
        eurBalance.setTotalBalance(BigDecimal.valueOf(2000));

        Map<Integer, Integer> eurDenomination = new HashMap<>();
        eurDenomination.put(10, 100);
        eurDenomination.put(50, 20);

        eurBalance.setDenomination(eurDenomination);

        return eurBalance;
    }
}

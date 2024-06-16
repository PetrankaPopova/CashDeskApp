package com.example.cashdeskapp.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.HashMap;
import java.util.Map;


/**
 * Configuration class that holds the banknotes denominations settings.
 * This class is used to map the properties defined with the prefix "banknotes" in the application configuration file.
 * It uses Lombok annotations to generate getter and setter methods.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "banknotes")
public class BanknotesDenominationsConfiguration {

    /**
     * A map representing the denominations of banknotes.
     * The key is the denomination value as a String, and the value is the quantity of that denomination.
     */
    private Map<String, Integer> denominations = new HashMap<>();
}

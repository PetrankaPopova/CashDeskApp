package com.example.cashdeskapp.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;


import java.util.HashMap;
import java.util.Map;


@Getter
@ConfigurationProperties(prefix = "banknotes")
public class BanknotesDenominationsConfiguration {
    private Map<String, Integer> denominations = new HashMap<>();

    public void setDenominations(Map<String, Integer> denominations) {
        this.denominations = denominations;
    }
}


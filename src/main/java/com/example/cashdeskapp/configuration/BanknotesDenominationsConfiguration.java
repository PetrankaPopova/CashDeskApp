package com.example.cashdeskapp.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@ConfigurationProperties(prefix = "banknotes")
public class BanknotesDenominationsConfiguration {
    private Map<String, Integer> denominations = new HashMap<>();

}


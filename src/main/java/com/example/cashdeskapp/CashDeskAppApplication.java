package com.example.cashdeskapp;

import com.example.cashdeskapp.configuration.BanknotesDenominationsConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(BanknotesDenominationsConfiguration.class)
public class CashDeskAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashDeskAppApplication.class, args);
    }

}

package com.example.cashdeskapp.configuration;

import com.example.cashdeskapp.mapper.CashBalanceMapper;
import com.example.cashdeskapp.mapper.impl.CashBalanceMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public CashBalanceMapper cashBalanceMapper() {
        return new CashBalanceMapperImpl();
    }
}

package com.example.cashdeskapp.configuration;

import com.example.cashdeskapp.mapper.CashBalanceMapper;
import com.example.cashdeskapp.mapper.impl.CashBalanceMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration class that defines bean configurations for the application.
 * This class is responsible for setting up the mapper beans used within the application.
 */
@Configuration
public class MapperConfiguration {

    /**
     * Creates and returns a {@link CashBalanceMapper} bean.
     * The {@link CashBalanceMapperImpl} implementation is used to provide the mapping functionality.
     *
     * @return an instance of {@link CashBalanceMapperImpl}
     */
    @Bean
    public CashBalanceMapper cashBalanceMapper() {
        return new CashBalanceMapperImpl();
    }
}
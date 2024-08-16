package com.synergybank.accounts.functions;

import com.synergybank.accounts.service.IAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunctions {

    @Bean
    public Consumer<Long> updateCommunication(IAccountService iAccountService){
        return (accountNumber) -> {
            System.out.println("Account number -> " + accountNumber);
            iAccountService.updateCommunication(accountNumber);
        };
    }
}

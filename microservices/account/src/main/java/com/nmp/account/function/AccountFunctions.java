package com.nmp.account.function;

import com.nmp.account.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountFunctions {
    private static final Logger logger = LoggerFactory.getLogger(AccountFunctions.class);

    @Bean
    public Consumer<Long> updateCommunication(IAccountService accountService) {
        return accountNumber -> {
            logger.info("Updating Communication status for the account number : " + accountNumber.toString());
            accountService.updateCommunicationStatus(accountNumber);
        };
    }
}
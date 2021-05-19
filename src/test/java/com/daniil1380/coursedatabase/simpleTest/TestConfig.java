package com.daniil1380.coursedatabase.simpleTest;

import com.daniil1380.coursedatabase.service.AccountService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
public class TestConfig {

    @Bean
    public AccountService accountService(){
        return new AccountService();
    }


}

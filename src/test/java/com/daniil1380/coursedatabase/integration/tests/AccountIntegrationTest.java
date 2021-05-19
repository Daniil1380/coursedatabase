package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.AccountApiImpl;
import com.daniil1380.coursedatabase.entity.AccountEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.model.Account;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountIntegrationTest {

    @Autowired
    AccountApiImpl accountApi;

    @ClassRule
    public static PostgreSQLContainer<InvestingPostgresqlContainer> postgreSQLContainer =
            InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkAccountPost(){
        List<Account> accountList = accountApi.getAccounts();
        int before = accountList.size();
        accountApi.postAccount(new AccountEntity(1, 100.98, true, 2, 3).toAccount());
        accountList = accountApi.getAccounts();
        Assert.assertEquals(before+1, accountList.size());
    }


    @Test
    @Transactional
    public void checkAccountGet(){
        List<Account> accountList = accountApi.getAccounts();
        Assert.assertNotNull(accountList);
        Assert.assertEquals(4, accountList.size());
        Assert.assertEquals(accountList.get(0),
                new AccountEntity(1,1, 145675.20, true, 1, 2).toAccount());
    }

    @Test
    @Transactional
    public void checkAccountDelete(){
        List<Account> accountList = accountApi.getAccounts();
        int before = accountList.size();
        accountApi.deleteAccount(1L);
        accountList = accountApi.getAccounts();
        Assert.assertEquals(before - 1, accountList.size());
    }






}

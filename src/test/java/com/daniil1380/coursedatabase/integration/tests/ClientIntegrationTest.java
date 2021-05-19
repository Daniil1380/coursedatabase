package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.*;
import com.daniil1380.coursedatabase.entity.ClientEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.model.User;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientIntegrationTest {

    @Autowired
    ClientApiImpl clientApi;

    @ClassRule
    public static PostgreSQLContainer<InvestingPostgresqlContainer> postgreSQLContainer =
            InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkClientPost(){
        List<User> userList = clientApi.getUsers();
        int before = userList.size();
        clientApi.postUser(new ClientEntity("NAME", "SURNAME", LocalDate.now()).toUser());
        userList = clientApi.getUsers();
        Assert.assertEquals(before+1, userList.size());
    }

    @Test
    @Transactional
    public void checkClientGet(){
        List<User> userList = clientApi.getUsers();
        Assert.assertNotNull(userList);
        Assert.assertEquals(4, userList.size());
        Assert.assertEquals(userList.get(0),
                new ClientEntity(1, "Daniil", "Tkachenko", LocalDate.parse("2001-04-24")).toUser());
    }
}

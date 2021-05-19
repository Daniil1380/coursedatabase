package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.*;
import com.daniil1380.coursedatabase.entity.BrokerEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.model.Broker;
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
public class BrokerIntegrationTest {

    @Autowired
    BrokerApiImpl brokerApi;

    @ClassRule
    public static PostgreSQLContainer<InvestingPostgresqlContainer> postgreSQLContainer =
            InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkBrokerPost() {
        List<Broker> brokerList = brokerApi.getBrokers();
        int before = brokerList.size();
        brokerApi.postBroker(new BrokerEntity("NAME_4", "COUNTRY_4", true).toBroker());
        brokerList = brokerApi.getBrokers();
        Assert.assertEquals(before + 1, brokerList.size());
    }

    @Test
    @Transactional
    public void checkBrokerGet() {
        List<Broker> brokerList = brokerApi.getBrokers();
        Assert.assertNotNull(brokerList);
        Assert.assertEquals(6, brokerList.size());
        Assert.assertEquals(brokerList.get(0), new BrokerEntity(1, "Тинькофф", "Россия", false)
                .toBroker());
    }


}

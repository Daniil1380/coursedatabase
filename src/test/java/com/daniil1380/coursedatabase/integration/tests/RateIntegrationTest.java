package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.OperationApiImpl;
import com.daniil1380.coursedatabase.api.RateApiImpl;
import com.daniil1380.coursedatabase.entity.OperationEntity;
import com.daniil1380.coursedatabase.entity.RateEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.ApiException;
import io.swagger.client.model.Operation;
import io.swagger.client.model.Rate;
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
public class RateIntegrationTest {

    @Autowired
    RateApiImpl rateApi;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkRatePost() throws ApiException {
        List<Rate> rateList = rateApi.getRates();
        int before = rateList.size();
        rateApi.postRate(new RateEntity("NAME", 0.03, 100, 1).toRate());
        rateList = rateApi.getRates();
        Assert.assertEquals(before+1, rateList.size());
    }

    @Test
    @Transactional
    public void checkOperationGet() throws Exception {
        List<Rate> rateList = rateApi.getRates();
        Assert.assertNotNull(rateList);
        Assert.assertEquals(5, rateList.size());
        Assert.assertEquals(rateList.get(0), new RateEntity(1,"Инвестор", 0.3, 0, 1).toRate());
    }
}

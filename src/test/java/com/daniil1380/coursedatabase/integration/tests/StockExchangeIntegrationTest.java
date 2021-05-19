package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.ShareApiImpl;
import com.daniil1380.coursedatabase.api.StockExchangeApiImpl;
import com.daniil1380.coursedatabase.entity.ShareEntity;
import com.daniil1380.coursedatabase.entity.StockExchangeEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.ApiException;
import io.swagger.client.model.Share;
import io.swagger.client.model.StockExchange;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockExchangeIntegrationTest {

    @Autowired
    StockExchangeApiImpl stockExchangeApi;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkStockExchangePost() throws ApiException {
        List<StockExchange> stockExchangeList = stockExchangeApi.getStockExchange();
        int before = stockExchangeList.size();
        stockExchangeApi.postStockExchange(new StockExchangeEntity("NAME", "TER", "CURRENCY", LocalTime.MIN, LocalTime.MAX).toStockExchange());
        stockExchangeList = stockExchangeApi.getStockExchange();
        Assert.assertEquals(before+1, stockExchangeList.size());
    }

    @Test
    @Transactional
    public void checkStockExchangeGet() throws Exception {
        List<StockExchange> stockExchangeList = stockExchangeApi.getStockExchange();
        Assert.assertNotNull(stockExchangeList);
        Assert.assertEquals(4, stockExchangeList.size());
        Assert.assertEquals(stockExchangeList.get(0), new StockExchangeEntity(1, "Московская биржа", "Россия", "RUB", LocalTime.MIN.withHour(10), LocalTime.MIN.withHour(18)).toStockExchange());
    }
}

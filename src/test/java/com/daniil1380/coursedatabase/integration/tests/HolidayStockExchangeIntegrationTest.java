package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.HolidayApiImpl;
import com.daniil1380.coursedatabase.api.HolidayStockExchangeApiImpl;
import com.daniil1380.coursedatabase.entity.HolidayEntity;
import com.daniil1380.coursedatabase.entity.HolidayStockExchangeEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.ApiException;
import io.swagger.client.model.Holiday;
import io.swagger.client.model.HolidayStockExchange;
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
public class HolidayStockExchangeIntegrationTest {

    @Autowired
    HolidayStockExchangeApiImpl holidayStockExchangeApi;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkHolidayStockExchangePost() throws ApiException {
        List<HolidayStockExchange> holidayStockExchangeList = holidayStockExchangeApi.getHolidaysStockExchange();
        int before = holidayStockExchangeList.size();
        holidayStockExchangeApi.postHolidayStockExchange(new HolidayStockExchangeEntity(4, 4).toHolidayStockExchange());
        holidayStockExchangeList = holidayStockExchangeApi.getHolidaysStockExchange();
        Assert.assertEquals(before+1, holidayStockExchangeList.size());
    }


    @Test
    @Transactional
    public void checkHolidayStockExchangeGet() throws Exception {
        List<HolidayStockExchange> holidayStockExchangeList = holidayStockExchangeApi.getHolidaysStockExchange();
        Assert.assertNotNull(holidayStockExchangeList);
        Assert.assertEquals(7, holidayStockExchangeList.size());
        Assert.assertEquals(holidayStockExchangeList.get(0), new HolidayStockExchangeEntity(1, 1).toHolidayStockExchange());
    }

    @Test
    @Transactional
    public void checkHolidayStockExchangeDelete() throws ApiException {
        List<HolidayStockExchange> holidayStockExchangeList = holidayStockExchangeApi.getHolidaysStockExchange();
        int before = holidayStockExchangeList.size();
        holidayStockExchangeApi.deleteHolidayStockExchange(1L, 1L);
        holidayStockExchangeList = holidayStockExchangeApi.getHolidaysStockExchange();
        Assert.assertEquals(before-1, holidayStockExchangeList.size());
    }






}

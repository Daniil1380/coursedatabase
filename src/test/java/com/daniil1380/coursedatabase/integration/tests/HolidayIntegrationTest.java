package com.daniil1380.coursedatabase.integration.tests;

import com.daniil1380.coursedatabase.api.HolidayApiImpl;
import com.daniil1380.coursedatabase.entity.HolidayEntity;
import com.daniil1380.coursedatabase.integration.InvestingPostgresqlContainer;
import io.swagger.client.model.Holiday;
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
public class HolidayIntegrationTest {

    @Autowired
    HolidayApiImpl holidayApi;

    @ClassRule
    public static PostgreSQLContainer<InvestingPostgresqlContainer> postgreSQLContainer =
            InvestingPostgresqlContainer.getInstance();


    @Test
    @Transactional
    public void checkHolidayPost(){
        List<Holiday> holidayList = holidayApi.getHolidays();
        int before = holidayList.size();
        holidayApi.postHoliday(new HolidayEntity(LocalDate.now()).toHoliday());
        holidayList = holidayApi.getHolidays();
        Assert.assertEquals(before+1, holidayList.size());
    }


    @Test
    @Transactional
    public void checkHolidayGet(){
        List<Holiday> holidayList = holidayApi.getHolidays();
        Assert.assertNotNull(holidayList);
        Assert.assertEquals(5, holidayList.size());
        Assert.assertEquals(holidayList.get(0), new HolidayEntity(1, LocalDate.parse("2021-09-23")).toHoliday());
    }






}

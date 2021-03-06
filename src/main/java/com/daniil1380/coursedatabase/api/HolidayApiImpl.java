package com.daniil1380.coursedatabase.api;

import com.daniil1380.coursedatabase.entity.HolidayEntity;
import com.daniil1380.coursedatabase.service.HolidayService;
import io.swagger.client.ApiException;
import io.swagger.client.api.HolidayApi;
import io.swagger.client.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HolidayApiImpl extends HolidayApi {
    @Autowired
    private HolidayService holidayService;


    @RequestMapping(value = "/holidays/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<Holiday> getHolidays() {
        return holidayService.listAll().stream().map(HolidayEntity::toHoliday).collect(Collectors.toList());
    }


    @RequestMapping(value = "/holidays/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public Holiday postHoliday(@RequestBody Holiday body) {
        holidayService.save(new HolidayEntity(body));
        return body;
    }
}

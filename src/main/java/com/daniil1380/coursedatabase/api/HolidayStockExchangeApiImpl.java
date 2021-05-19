package com.daniil1380.coursedatabase.api;

import com.daniil1380.coursedatabase.entity.HolidayEntity;
import com.daniil1380.coursedatabase.entity.HolidayStockExchangeEntity;
import com.daniil1380.coursedatabase.entity.HolidayStockExchangeIdClass;
import com.daniil1380.coursedatabase.service.HolidayStockExchangeService;
import io.swagger.client.ApiException;
import io.swagger.client.api.HolidayStockExchangeApi;
import io.swagger.client.model.HolidayStockExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class HolidayStockExchangeApiImpl extends HolidayStockExchangeApi {
    @Autowired
    private HolidayStockExchangeService holidayStockExchangeService;

    @RequestMapping(value = "/holidaysStockExchange/{holidayStockExchangeId}/{holidayId}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    @Override
    public void deleteHolidayStockExchange(@PathVariable("holidayStockExchangeId") Long holidayStockExchangeId, @PathVariable("holidayId") Long holidayId) throws ApiException {
        HolidayStockExchangeIdClass holidayStockExchangeIdClass = new HolidayStockExchangeIdClass();
        holidayStockExchangeIdClass.setHolidayId(holidayId.intValue());
        holidayStockExchangeIdClass.setStockExchangeId(holidayStockExchangeId.intValue());
        if (holidayStockExchangeService.get(holidayStockExchangeIdClass).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found");
        }
        holidayStockExchangeService.delete(holidayStockExchangeIdClass);
    }

    @RequestMapping(value = "/holidaysStockExchange/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<HolidayStockExchange> getHolidaysStockExchange() throws ApiException {
        return holidayStockExchangeService.listAll()
                .stream()
                .map(HolidayStockExchangeEntity::toHolidayStockExchange)
                .collect(Collectors.toList());

    }

    @RequestMapping(value = "/holidaysStockExchange/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public HolidayStockExchange postHolidayStockExchange(@RequestBody HolidayStockExchange body) throws ApiException {
        holidayStockExchangeService.save(new HolidayStockExchangeEntity(body));
        return body;
    }
}

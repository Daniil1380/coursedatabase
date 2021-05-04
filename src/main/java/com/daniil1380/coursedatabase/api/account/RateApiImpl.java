package com.daniil1380.coursedatabase.api.account;

import com.daniil1380.coursedatabase.entity.HolidayStockExchangeEntity;
import com.daniil1380.coursedatabase.entity.RateEntity;
import com.daniil1380.coursedatabase.service.RateService;
import io.swagger.client.ApiException;
import io.swagger.client.api.RateApi;
import io.swagger.client.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RateApiImpl extends RateApi {
    @Autowired
    private RateService rateService;

    @RequestMapping(value = "/rates/{rateId}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    @Override
    public void deleteRate(@PathVariable("rateId") Long rateId) throws ApiException {
        rateService.delete(Math.toIntExact(rateId));
    }


    @RequestMapping(value = "/rates/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<Rate> getRates() throws ApiException {
        return rateService.listAll()
                .stream()
                .map(RateEntity::toRate)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/rates/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public Rate postRate(@RequestBody Rate body) throws ApiException {
        rateService.save(new RateEntity(body));
        return body;
    }
}

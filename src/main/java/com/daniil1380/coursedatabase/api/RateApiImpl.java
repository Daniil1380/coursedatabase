package com.daniil1380.coursedatabase.api;

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


    @RequestMapping(value = "/rates/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<Rate> getRates(){
        return rateService.listAll()
                .stream()
                .map(RateEntity::toRate)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/rates/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public Rate postRate(@RequestBody Rate body){
        rateService.save(new RateEntity(body));
        return body;
    }
}

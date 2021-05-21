package com.daniil1380.coursedatabase.api;

import com.daniil1380.coursedatabase.entity.RateEntity;
import com.daniil1380.coursedatabase.service.BrokerService;
import com.daniil1380.coursedatabase.service.RateService;
import io.swagger.client.ApiException;
import io.swagger.client.api.RateApi;
import io.swagger.client.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RateApiImpl extends RateApi {
    @Autowired
    private RateService rateService;

    @Autowired
    private BrokerService brokerService;


    @RequestMapping(value = "/rates/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Override
    public List<Rate> getRates() {
        return rateService.listAll()
                .stream()
                .map(RateEntity::toRate)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/rates/",
            produces = {"application/json"},
            method = RequestMethod.POST)
    @Override
    public Rate postRate(@RequestBody Rate body) {
        rateService.save(new RateEntity(body));
        return body;
    }


    @RequestMapping(value = "/rates/lessUsed/{brokerId}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    @Override
    public void deleteLessUsedRate(@PathVariable("brokerId") Long brokerId) throws ApiException {
        if (brokerService.get(brokerId.intValue()).isPresent()) {
            rateService.deleteLessUsed(brokerId.intValue());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not Found");
        }
    }
}

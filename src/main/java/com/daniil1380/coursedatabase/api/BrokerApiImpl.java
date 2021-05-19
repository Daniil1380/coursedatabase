package com.daniil1380.coursedatabase.api;

import com.daniil1380.coursedatabase.entity.AccountEntity;
import com.daniil1380.coursedatabase.entity.BrokerEntity;
import com.daniil1380.coursedatabase.service.BrokerService;
import io.swagger.client.ApiException;
import io.swagger.client.api.BrokerApi;
import io.swagger.client.model.Broker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BrokerApiImpl extends BrokerApi {
    @Autowired
    private BrokerService brokerService;


    @RequestMapping(value = "/brokers/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<Broker> getBrokers() throws ApiException {
        return brokerService.listAll().stream().map(BrokerEntity::toBroker).collect(Collectors.toList());
    }

    @RequestMapping(value = "/brokers/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public Broker postBroker(@RequestBody Broker body) throws ApiException {
        brokerService.save(new BrokerEntity(body));
        return body;
    }
}

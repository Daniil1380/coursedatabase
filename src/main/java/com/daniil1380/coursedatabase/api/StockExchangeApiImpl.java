package com.daniil1380.coursedatabase.api;

import com.daniil1380.coursedatabase.entity.StockExchangeEntity;
import com.daniil1380.coursedatabase.service.StockExchangeService;
import io.swagger.client.api.StockExchangeApi;
import io.swagger.client.model.StockExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StockExchangeApiImpl extends StockExchangeApi {

    @Autowired
    private StockExchangeService stockExchangeService;


    @RequestMapping(value = "/stockExchanges/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<StockExchange> getStockExchange(){
        return stockExchangeService.listAll()
                .stream()
                .map(StockExchangeEntity::toStockExchange)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/stockExchanges/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public StockExchange postStockExchange(@RequestBody StockExchange body){
        stockExchangeService.save(new StockExchangeEntity(body));
        return body;
    }
}

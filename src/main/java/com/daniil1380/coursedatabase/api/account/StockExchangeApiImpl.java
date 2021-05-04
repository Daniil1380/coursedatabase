package com.daniil1380.coursedatabase.api.account;

import com.daniil1380.coursedatabase.entity.HolidayStockExchangeEntity;
import com.daniil1380.coursedatabase.entity.StockExchangeEntity;
import com.daniil1380.coursedatabase.service.StockExchangeService;
import io.swagger.client.ApiException;
import io.swagger.client.api.StockExchangeApi;
import io.swagger.client.model.StockExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class StockExchangeApiImpl extends StockExchangeApi {

    @Autowired
    private StockExchangeService stockExchangeService;

    @RequestMapping(value = "/stockExchanges/{stockExchangeId}",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    @Override
    public void deleteStockExchange(@PathVariable("stockExchangeId") Long stockExchangeId) throws ApiException {
        if (stockExchangeService.get(stockExchangeId.intValue()).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found");
        }
        stockExchangeService.delete(Math.toIntExact(stockExchangeId));
    }

    @RequestMapping(value = "/stockExchanges/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    @Override
    public List<StockExchange> getStockExchange() throws ApiException {
        return stockExchangeService.listAll()
                .stream()
                .map(StockExchangeEntity::toStockExchange)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/stockExchanges/",
            produces = { "application/json" },
            method = RequestMethod.POST)
    @Override
    public StockExchange postStockExchange(@RequestBody StockExchange body) throws ApiException {
        stockExchangeService.save(new StockExchangeEntity(body));
        return body;
    }
}

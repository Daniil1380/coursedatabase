package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Operation;
import io.swagger.client.model.StockExchange;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@javax.persistence.Entity
@Table(name = "stock_exchange", schema = "public")
public class StockExchangeEntity extends Entity{
    private int id;
    private String name;
    private String territory;
    private String currency;
    private LocalTime dayStart;
    private LocalTime dayEnd;


    public StockExchangeEntity() {
    }

    public StockExchangeEntity(StockExchange stockExchange) {
        name = stockExchange.getName();
        territory = stockExchange.getTerritory();
        currency = stockExchange.getCurrency();
        dayStart = LocalTime.parse(stockExchange.getDayStart());
        dayEnd = LocalTime.parse(stockExchange.getDayFinish());
    }

    public StockExchange toStockExchange(){
        StockExchange stockExchange = new StockExchange();
        stockExchange.setId(id);
        stockExchange.setName(name);
        stockExchange.setTerritory(territory);
        stockExchange.setCurrency(currency);
        stockExchange.dayStart(dayStart.toString());
        stockExchange.setDayFinish(dayEnd.toString());
        return stockExchange;
    }

    @Id
    @Basic
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "territory")
    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "day_start")
    public LocalTime getDayStart() {
        return dayStart;
    }

    public void setDayStart(LocalTime dayStart) {
        this.dayStart = dayStart;
    }

    @Basic
    @Column(name = "day_end")
    public LocalTime getDayEnd() {
        return dayEnd;
    }

    public void setDayEnd(LocalTime dayEnd) {
        this.dayEnd = dayEnd;
    }

    @Override
    public String generateSQLString() {
        return String.format("INSERT INTO stock_exchange (name, territory, currency) VALUES ('%s', '%s', '%s');", name, territory, currency);
    }
}

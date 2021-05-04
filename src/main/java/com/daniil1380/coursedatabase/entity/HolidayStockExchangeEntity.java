package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Account;
import io.swagger.client.model.HolidayStockExchange;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Table(name = "holiday_stock_exchange", schema = "public")
@IdClass(HolidayStockExchangeIdClass.class)
public class HolidayStockExchangeEntity extends Entity implements Serializable {
    @Id
    @Basic
    @Column(name = "holiday_id")
    private int holidayId;

    @Id
    @Basic
    @Column(name = "stock_exchange")
    private int stockExchangeId;


    public HolidayStockExchangeEntity() {
    }

    public HolidayStockExchangeEntity(HolidayStockExchange holidayStockExchange) {
        holidayId = holidayStockExchange.getHolidayId();
        stockExchangeId = holidayStockExchange.getStockExchangeId();
    }

    public HolidayStockExchange toHolidayStockExchange(){
        HolidayStockExchange holidayStockExchange = new HolidayStockExchange();
        holidayStockExchange.setHolidayId(holidayId);
        holidayStockExchange.setStockExchangeId(stockExchangeId);
        return holidayStockExchange;
    }

    public int getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(int holidayId) {
        this.holidayId = holidayId;
    }

    public int getStockExchangeId() {
        return stockExchangeId;
    }

    public void setStockExchangeId(int stockExchangeId) {
        this.stockExchangeId = stockExchangeId;
    }

    @Override
    public String generateSQLString() {
        return String.format("INSERT INTO holiday_stock_exchange (holiday_id, stock_exchange) VALUES ('%s', '%s');", holidayId, stockExchangeId);
    }
}
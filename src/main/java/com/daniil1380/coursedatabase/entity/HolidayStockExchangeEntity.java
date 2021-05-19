package com.daniil1380.coursedatabase.entity;



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

    public HolidayStockExchangeEntity(int holidayId, int stockExchangeId) {
        this.holidayId = holidayId;
        this.stockExchangeId = stockExchangeId;
    }

    public HolidayStockExchange toHolidayStockExchange(){
        HolidayStockExchange holidayStockExchange = new HolidayStockExchange();
        holidayStockExchange.setHolidayId(holidayId);
        holidayStockExchange.setStockExchangeId(stockExchangeId);
        return holidayStockExchange;
    }

}

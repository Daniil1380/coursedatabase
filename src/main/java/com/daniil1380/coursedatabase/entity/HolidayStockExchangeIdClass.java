package com.daniil1380.coursedatabase.entity;

import java.io.Serializable;

public class HolidayStockExchangeIdClass implements Serializable {
    private int holidayId;
    private int stockExchangeId;

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
}

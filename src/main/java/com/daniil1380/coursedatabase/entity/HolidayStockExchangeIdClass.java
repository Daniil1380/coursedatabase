package com.daniil1380.coursedatabase.entity;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HolidayStockExchangeIdClass that = (HolidayStockExchangeIdClass) o;
        return holidayId == that.holidayId && stockExchangeId == that.stockExchangeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(holidayId, stockExchangeId);
    }
}

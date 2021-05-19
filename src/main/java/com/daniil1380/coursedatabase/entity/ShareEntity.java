package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Operation;
import io.swagger.client.model.Share;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;

@javax.persistence.Entity
@Table(name = "share", schema = "public")
public class ShareEntity extends Entity{
    private int id;
    private String name;
    private int stockExchangeId;
    private double cost;
    private int count;


    public ShareEntity() {
    }

    public ShareEntity(Share share) {
        name  = share.getName();
        stockExchangeId = share.getStockExchangeId();
        cost = share.getCost().doubleValue();
        count = share.getCount();
    }

    public ShareEntity(int id, String name, int stockExchangeId, double cost, int count) {
        this.id = id;
        this.name = name;
        this.stockExchangeId = stockExchangeId;
        this.cost = cost;
        this.count = count;
    }

    public ShareEntity(String name, int stockExchangeId, double cost, int count) {
        this.name = name;
        this.stockExchangeId = stockExchangeId;
        this.cost = cost;
        this.count = count;
    }

    public Share toShare(){
        Share share = new Share();
        share.setId(id);
        share.setName(name);
        share.setCost(new BigDecimal(cost, MathContext.DECIMAL64));
        share.setCount(count);
        share.setStockExchangeId(stockExchangeId);
        return share;
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
    @Column(name = "stock_exchange_id")
    public int getStockExchangeId() {
        return stockExchangeId;
    }

    public void setStockExchangeId(int stockExchangeId) {
        this.stockExchangeId = stockExchangeId;
    }

    @Basic
    @Column(name = "cost")
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    @Basic
    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

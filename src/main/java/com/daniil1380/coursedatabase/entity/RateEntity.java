package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Operation;
import io.swagger.client.model.Rate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;

@javax.persistence.Entity
@Table(name = "rate", schema = "public")
public class RateEntity extends Entity{
    private int id;
    private String name;
    private double commission;
    private int cost;
    private int brokerId;

    public RateEntity() {
    }

    public RateEntity(Rate rate) {
        name = rate.getName();
        commission = rate.getCommission().doubleValue();
        cost = rate.getCost();
        brokerId = rate.getBrokerId();
    }

    public RateEntity(int id, String name, double commission, int cost, int brokerId) {
        this.id = id;
        this.name = name;
        this.commission = commission;
        this.cost = cost;
        this.brokerId = brokerId;
    }

    public RateEntity(String name, double commission, int cost, int brokerId) {
        this.name = name;
        this.commission = commission;
        this.cost = cost;
        this.brokerId = brokerId;
    }

    public Rate toRate(){
        Rate rate = new Rate();
        rate.setId(id);
        rate.setBrokerId(brokerId);
        rate.setCommission(new BigDecimal(commission, MathContext.DECIMAL64));
        rate.setCost(cost);
        rate.setName(name);
        return rate;
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
    @Column(name = "commission")
    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    @Basic
    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "broker_id")
    public int getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

}

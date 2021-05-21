package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.AccountWithBrokerId;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class AccountWithBrokerIdEntity {


    public AccountWithBrokerIdEntity() {
    }

    private int id;
    private double cash;
    private String name;
    private String rate;

    public AccountWithBrokerIdEntity(int id, double cash, String name, String rate) {
        this.id = id;
        this.cash = cash;
        this.name = name;
        this.rate = rate;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public void setName(String broker) {
        this.name = broker;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }


    public AccountWithBrokerId toAccountWithBrokerId(){
        AccountWithBrokerId account = new AccountWithBrokerId();
        account.setCash(BigDecimal.valueOf(cash));
        account.setId(id);
        account.setRate(rate);
        account.setName(name);
        return account;
    }
}

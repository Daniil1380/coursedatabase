package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Account;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;

@javax.persistence.Entity
@Table(name = "account", schema = "public")
public class AccountEntity extends Entity{

    private int id;
    private int brokerId;
    private double cash;
    private boolean margin;
    private int rateId;
    private int clientId;

    public AccountEntity() {
    }

    public AccountEntity(Account account) {
        brokerId = account.getBrokerId();
        cash = account.getCash().doubleValue();
        margin = account.isMargin();
        rateId = account.getRateId();
        clientId = account.getUserId();
    }



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Basic
    @Column(name = "broker_id")
    public int getBrokerId() {
        return brokerId;
    }

    @Basic
    @Column(name = "cash")
    public double getCash() {
        return cash;
    }

    @Basic
    @Column(name = "margin")
    public boolean isMargin() {
        return margin;
    }

    @Basic
    @Column(name = "rate_id")
    public int getRateId() {
        return rateId;
    }

    @Basic
    @Column(name = "client_id")
    public int getClientId() {
        return clientId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBrokerId(int brokerId) {
        this.brokerId = brokerId;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setMargin(boolean margin) {
        this.margin = margin;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public String generateSQLString() {
        return String.format("INSERT INTO account (broker_id, cash, margin, rate_id, client_id) VALUES (%s, %s, %s, %s, %s);", brokerId, cash, margin, rateId, clientId);
    }


    public Account toAccount(){
        Account account = new Account();
        account.setId(id);
        account.setBrokerId(brokerId);
        account.setCash(new BigDecimal(cash, MathContext.DECIMAL64));
        account.setRateId(rateId);
        account.setMargin(margin);
        account.setUserId(clientId);
        return account;
    }
}

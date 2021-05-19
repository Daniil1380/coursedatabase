package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Operation;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "operation", schema = "public")
public class OperationEntity extends Entity{
    private int id;
    private int clientId;
    private int shareId;
    private int count;
    private String buySell;

    public OperationEntity() {
    }

    public OperationEntity(Operation operation) {
        clientId = operation.getUserId();
        shareId = operation.getShareId();
        count = operation.getCount();
        buySell = operation.getBuySell();
    }

    public OperationEntity(int id, int clientId, int shareId, int count, String buySell) {
        this.id = id;
        this.clientId = clientId;
        this.shareId = shareId;
        this.count = count;
        this.buySell = buySell;
    }

    public OperationEntity(int clientId, int shareId, int count, String buySell) {
        this.clientId = clientId;
        this.shareId = shareId;
        this.count = count;
        this.buySell = buySell;
    }

    public Operation toOperation(){
        Operation operation = new Operation();
        operation.setId(id);
        operation.setUserId(clientId);
        operation.setShareId(shareId);
        operation.setCount(count);
        operation.setBuySell(buySell);
        return operation;
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
    @Column(name = "client_id")
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "share_id")
    public int getShareId() {
        return shareId;
    }

    public void setShareId(int shareId) {
        this.shareId = shareId;
    }

    @Basic
    @Column(name = "count")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "buy_sell")
    public String getBuySell() {
        return buySell;
    }

    public void setBuySell(String buySell) {
        this.buySell = buySell;
    }


}

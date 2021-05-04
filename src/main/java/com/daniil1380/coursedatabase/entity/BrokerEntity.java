package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Account;
import io.swagger.client.model.Broker;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "broker", schema = "public")
public class BrokerEntity extends Entity{
    private int id;
    private String name;
    private String country;
    private boolean state;


    public BrokerEntity() {
    }

    public BrokerEntity(Broker broker) {
        name = broker.getName();
        country = broker.getCountry();
        state = broker.isState();
    }

    public Broker toBroker(){
        Broker broker = new Broker();
        broker.setId(id);
        broker.setCountry(country);
        broker.setState(state);
        broker.setName(name);
        return broker;
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
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "state")
    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String generateSQLString() {
        return String.format("INSERT INTO broker (name, country, state) VALUES ('%s', '%s', '%s');", name, country, state);
    }
}

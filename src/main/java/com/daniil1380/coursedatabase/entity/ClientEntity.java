package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Account;
import io.swagger.client.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@javax.persistence.Entity
@Table(name = "client", schema = "public")
public class ClientEntity extends Entity {

    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;

    public ClientEntity() {
    }

    public ClientEntity(User user) {
        name = user.getName();
        surname = user.getSurname();
        birthDate = LocalDate.parse(user.getBirthDate());
    }

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setBirthDate(birthDate.toString());
        user.setSurname(surname);
        user.setName(name);
        return user;
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
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Basic
    @Column(name = "birth_date")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String generateSQLString(){
        return String.format("INSERT INTO client (name, surname, birth_date) VALUES ('%s', '%s', '%s');", name, surname, birthDate);
    }
}

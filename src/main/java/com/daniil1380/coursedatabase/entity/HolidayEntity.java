package com.daniil1380.coursedatabase.entity;


import io.swagger.client.model.Holiday;

import javax.persistence.*;
import java.time.LocalDate;

@javax.persistence.Entity
@Table(name = "holiday", schema = "public")
public class HolidayEntity extends Entity{

    private int id;
    private LocalDate day;

    public HolidayEntity() {
    }

    public HolidayEntity(int id, LocalDate day) {
        this.id = id;
        this.day = day;
    }

    public HolidayEntity(LocalDate day) {
        this.day = day;
    }

    public HolidayEntity(Holiday holiday) {
        day = LocalDate.parse(holiday.getDate());
    }

    public Holiday toHoliday(){
        Holiday holiday = new Holiday();
        holiday.setDate(day.toString());
        holiday.setId(id);
        return holiday;
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
    @Column(name = "day")
    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }



   }

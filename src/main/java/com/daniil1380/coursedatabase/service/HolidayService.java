package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.AccountEntity;
import com.daniil1380.coursedatabase.entity.HolidayEntity;
import com.daniil1380.coursedatabase.repository.AccountRepository;
import com.daniil1380.coursedatabase.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HolidayService {

    @Autowired
    HolidayRepository repo;

    public void save(HolidayEntity holidayEntity) {
        repo.save(holidayEntity);
    }

    public List<HolidayEntity> listAll() {
        return (List<HolidayEntity>) repo.findAll();
    }

    public HolidayEntity get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

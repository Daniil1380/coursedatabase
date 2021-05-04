package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.ClientEntity;
import com.daniil1380.coursedatabase.entity.RateEntity;
import com.daniil1380.coursedatabase.repository.ClientRepository;
import com.daniil1380.coursedatabase.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RateService {

    @Autowired
    RateRepository repo;

    public void save(RateEntity rateEntity) {
        repo.save(rateEntity);
    }

    public List<RateEntity> listAll() {
        return (List<RateEntity>) repo.findAll();
    }

    public RateEntity get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
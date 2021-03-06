package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.BrokerEntity;
import com.daniil1380.coursedatabase.repository.BrokerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrokerService {

    @Autowired
    BrokerRepository repo;


    @CacheEvict("brokers")
    public void save(BrokerEntity brokerEntity) {
        repo.save(brokerEntity);
    }

    @Cacheable("brokers")
    public List<BrokerEntity> listAll() {
        return (List<BrokerEntity>) repo.findAll();
    }

    public Optional<BrokerEntity> get(Integer id) {
        return repo.findById(id);
    }
    @CacheEvict("brokers")
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

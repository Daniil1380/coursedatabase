package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.ClientEntity;
import com.daniil1380.coursedatabase.entity.StockExchangeEntity;
import com.daniil1380.coursedatabase.repository.StockExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockExchangeService {

    @Autowired
    StockExchangeRepository repo;

    @CacheEvict("stockExchanges")
    public void save(StockExchangeEntity stockExchangeEntity) {
        repo.save(stockExchangeEntity);
    }

    @Cacheable("stockExchanges")
    public List<StockExchangeEntity> listAll() {
        return (List<StockExchangeEntity>) repo.findAll();
    }

    public Optional<StockExchangeEntity> get(Integer id) {
        return repo.findById(id);
    }

    @CacheEvict("stockExchanges")
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

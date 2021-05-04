package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.AccountEntity;
import com.daniil1380.coursedatabase.entity.HolidayStockExchangeEntity;
import com.daniil1380.coursedatabase.entity.HolidayStockExchangeIdClass;
import com.daniil1380.coursedatabase.repository.AccountRepository;
import com.daniil1380.coursedatabase.repository.HolidayStockExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HolidayStockExchangeService {

    @Autowired
    HolidayStockExchangeRepository repo;

    @CacheEvict("holidaysStockExchange")
    public void save(HolidayStockExchangeEntity holidayStockExchangeEntity) {
        repo.save(holidayStockExchangeEntity);
    }

    @Cacheable("holidaysStockExchange")
    public List<HolidayStockExchangeEntity> listAll() {
        return (List<HolidayStockExchangeEntity>) repo.findAll();
    }

    public Optional<HolidayStockExchangeEntity> get(HolidayStockExchangeIdClass id) {
        return repo.findById(id);
    }

    @CacheEvict("holidaysStockExchange")
    public void delete(HolidayStockExchangeIdClass id) {
        repo.deleteById(id);
    }
}

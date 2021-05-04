package com.daniil1380.coursedatabase.repository;

import com.daniil1380.coursedatabase.entity.StockExchangeEntity;
import org.springframework.data.repository.CrudRepository;

public interface StockExchangeRepository extends CrudRepository<StockExchangeEntity, Integer> {
}

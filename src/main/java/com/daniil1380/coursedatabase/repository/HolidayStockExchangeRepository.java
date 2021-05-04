package com.daniil1380.coursedatabase.repository;

import com.daniil1380.coursedatabase.entity.HolidayStockExchangeEntity;
import com.daniil1380.coursedatabase.entity.HolidayStockExchangeIdClass;
import org.springframework.data.repository.CrudRepository;

public interface HolidayStockExchangeRepository extends CrudRepository<HolidayStockExchangeEntity, HolidayStockExchangeIdClass> {
}

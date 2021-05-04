package com.daniil1380.coursedatabase.repository;

import com.daniil1380.coursedatabase.entity.OperationEntity;
import org.springframework.data.repository.CrudRepository;

public interface OperationRepository extends CrudRepository<OperationEntity, Integer> {
}

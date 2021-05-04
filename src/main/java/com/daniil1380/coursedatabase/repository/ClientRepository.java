package com.daniil1380.coursedatabase.repository;

import com.daniil1380.coursedatabase.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
}

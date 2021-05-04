package com.daniil1380.coursedatabase.repository;

import com.daniil1380.coursedatabase.entity.AccountEntity;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {

}

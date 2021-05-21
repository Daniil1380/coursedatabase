package com.daniil1380.coursedatabase.repository;

import com.daniil1380.coursedatabase.entity.ShareEntity;
import com.daniil1380.coursedatabase.entity.TopSharesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShareRepository extends CrudRepository<ShareEntity, Integer> {

    @Query("from TopSharesEntity")
    List<TopSharesEntity> getTopTenShares();

}

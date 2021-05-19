package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.OperationEntity;
import com.daniil1380.coursedatabase.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperationService {

    @Autowired
    OperationRepository repo;

    @CacheEvict("operations")
    public void save(OperationEntity operationEntity) {
        repo.save(operationEntity);
    }

    @Cacheable("operations")
    public List<OperationEntity> listAll() {
        return (List<OperationEntity>) repo.findAll();
    }

    public Optional<OperationEntity> get(Integer id) {
        return repo.findById(id);
    }

    @CacheEvict("operations")
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.AccountEntity;
import com.daniil1380.coursedatabase.entity.OperationEntity;
import com.daniil1380.coursedatabase.repository.AccountRepository;
import com.daniil1380.coursedatabase.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OperationService {

    @Autowired
    OperationRepository repo;

    public void save(OperationEntity operationEntity) {
        repo.save(operationEntity);
    }

    public List<OperationEntity> listAll() {
        return (List<OperationEntity>) repo.findAll();
    }

    public OperationEntity get(Integer id) {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
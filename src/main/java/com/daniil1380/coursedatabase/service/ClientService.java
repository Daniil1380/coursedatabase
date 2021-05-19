package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.ClientEntity;
import com.daniil1380.coursedatabase.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    @Autowired
    ClientRepository repo;


    public void save(ClientEntity clientEntity) {
        repo.save(clientEntity);
    }

    public List<ClientEntity> listAll() {
        return (List<ClientEntity>) repo.findAll();
    }

    public Optional<ClientEntity> get(Integer id) {
        return repo.findById(id);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

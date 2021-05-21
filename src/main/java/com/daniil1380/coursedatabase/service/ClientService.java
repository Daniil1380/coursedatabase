package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.AccountWithBrokerIdEntity;
import com.daniil1380.coursedatabase.entity.ClientEntity;
import com.daniil1380.coursedatabase.repository.AccountWithBrokerIdRepository;
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
    ClientRepository clientRepository;

    @Autowired
    AccountWithBrokerIdRepository accountWithBrokerIdRepository;

    public void save(ClientEntity clientEntity) {
        clientRepository.save(clientEntity);
    }

    public List<ClientEntity> listAll() {
        return (List<ClientEntity>) clientRepository.findAll();
    }

    public Optional<ClientEntity> get(Integer id) {
        return clientRepository.findById(id);
    }

    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }

    public List<AccountWithBrokerIdEntity> getAllAccountsByClient(int id){
        return accountWithBrokerIdRepository.getAccountWithBrokerIdEntity(id);
    }
}

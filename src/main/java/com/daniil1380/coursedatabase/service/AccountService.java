package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.AccountEntity;
import com.daniil1380.coursedatabase.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository repo;

    @CacheEvict("accounts")
    public void save(AccountEntity account) {
        repo.save(account);
    }

    @Cacheable("accounts")
    public List<AccountEntity> listAll() {
        return (List<AccountEntity>) repo.findAll();
    }

    public Optional<AccountEntity> get(Integer id) {
        return repo.findById(id);
    }

    @CacheEvict("accounts")
    public void delete(Integer id) { repo.deleteById(id); }
}

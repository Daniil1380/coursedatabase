package com.daniil1380.coursedatabase.service;

import com.daniil1380.coursedatabase.entity.ClientEntity;
import com.daniil1380.coursedatabase.entity.ShareEntity;
import com.daniil1380.coursedatabase.repository.ClientRepository;
import com.daniil1380.coursedatabase.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShareService {

    @Autowired
    ShareRepository repo;

    public void save(ShareEntity shareEntity) { repo.save(shareEntity);
    }

    public List<ShareEntity> listAll() {
        return (List<ShareEntity>) repo.findAll();
    }

    public Optional<ShareEntity> get(Integer id) {
        return repo.findById(id);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

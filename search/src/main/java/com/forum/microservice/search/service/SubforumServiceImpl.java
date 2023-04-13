package com.forum.microservice.search.service;

import com.forum.microservice.search.entity.SubforumEntity;
import com.forum.microservice.search.exceptions.SubforumNotFoundException;
import com.forum.microservice.search.repository.SubforumRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubforumServiceImpl implements com.forum.microservice.search.service.SubforumService {

    private SubforumRepository subforumRepository;

    @Autowired
    public SubforumServiceImpl(SubforumRepository subforumRepository) {
        this.subforumRepository = subforumRepository;
    }

    @Override
    public List<SubforumEntity> findAll() {
        return subforumRepository.findAll();
    }

    @Override
    public SubforumEntity findById(int id) {
        Optional<SubforumEntity> foundEntity = subforumRepository.findById(id);
        if (foundEntity.isPresent()) {
            return foundEntity.get();
        } else {
            throw new SubforumNotFoundException("Subforum id not found: " + id);
        }
    }

    @Override
    public SubforumEntity save(SubforumEntity subforumEntity) {
        return subforumRepository.save(subforumEntity);
    }

    @Override
    public SubforumEntity getSubforumBySubstring(String name) {
        return subforumRepository.getSubforumContainsSubstring(name);
    }

    @Override
    public void deleteById(int id) {
        subforumRepository.deleteById(id);
    }
}

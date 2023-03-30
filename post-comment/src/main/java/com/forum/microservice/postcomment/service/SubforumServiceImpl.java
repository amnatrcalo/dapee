package com.forum.microservice.postcomment.service;

import com.forum.microservice.postcomment.entity.SubforumEntity;
import com.forum.microservice.postcomment.exceptions.SubforumNotFoundException;
import com.forum.microservice.postcomment.repository.SubforumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubforumServiceImpl implements SubforumService {
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
  public void deleteById(int id) {
    subforumRepository.deleteById(id);
  }
}

package com.forum.microservice.administration.service;

import com.forum.microservice.administration.entity.SubforumEntity;
import java.util.List;

public interface SubforumService {
  List<SubforumEntity> findAll();

  SubforumEntity findById(int id);

  SubforumEntity save(SubforumEntity subforumEntity);

  void deleteById(int id);

  SubforumEntity getSubforumByName(String name);
}

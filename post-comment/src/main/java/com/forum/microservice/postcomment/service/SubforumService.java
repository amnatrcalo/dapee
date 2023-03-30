package com.forum.microservice.postcomment.service;


import com.forum.microservice.postcomment.entity.SubforumEntity;

import java.util.List;

public interface SubforumService {
  List<SubforumEntity> findAll();

  SubforumEntity findById(int id);

  SubforumEntity save(SubforumEntity subforumEntity);

  void deleteById(int id);
}

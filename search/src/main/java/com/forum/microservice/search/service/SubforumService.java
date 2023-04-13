package com.forum.microservice.search.service;

import com.forum.microservice.search.entity.SubforumEntity;
import java.util.List;

public interface SubforumService {
    List<SubforumEntity> findAll();

    SubforumEntity findById(int id);

    SubforumEntity save(SubforumEntity subforumEntity);

    SubforumEntity getSubforumBySubstring(String name);

    void deleteById(int id);
}

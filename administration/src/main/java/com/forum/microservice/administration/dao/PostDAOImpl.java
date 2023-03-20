package com.forum.microservice.administration.dao;

import com.forum.microservice.administration.entity.PostEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostDAOImpl implements PostDAO {
  private EntityManager entityManager;

  @Autowired
  public PostDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void save(PostEntity post) {
    entityManager.persist(post);
  }
}

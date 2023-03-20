package com.forum.microservice.administration.dao;

import com.forum.microservice.administration.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDao {
  private EntityManager entityManager;

  @Autowired
  public UserDAOImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void save(UserEntity userEntity) {
    entityManager.persist(userEntity);
  }
}

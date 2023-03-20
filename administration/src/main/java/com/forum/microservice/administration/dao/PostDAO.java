package com.forum.microservice.administration.dao;

import com.forum.microservice.administration.entity.PostEntity;

public interface PostDAO {
  void save(PostEntity post);
}

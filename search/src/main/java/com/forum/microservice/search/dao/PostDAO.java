package com.forum.microservice.search.dao;

import com.forum.microservice.search.entity.PostEntity;

public interface PostDAO {
    void save(PostEntity post);
}


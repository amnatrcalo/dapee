package com.forum.microservice.postcomment.service;

import com.forum.microservice.postcomment.entity.HashtagEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.exceptions.HashtagNotFoundException;
import com.forum.microservice.postcomment.exceptions.PostNotFoundException;
import com.forum.microservice.postcomment.repository.HashtagRepository;
import com.forum.microservice.postcomment.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HashtagServiceImpl implements HashtagService {
    private HashtagRepository hashtagRepository;
    private PostRepository postRepository;

    @Autowired
    public HashtagServiceImpl(
            PostRepository postRepository,
            HashtagRepository hashtagRepository) {
        this.postRepository = postRepository;
        this.hashtagRepository = hashtagRepository;
    }

    @Override
    public List<HashtagEntity> findAll() {
        return hashtagRepository.findAll();
    }

    @Override
    public HashtagEntity findById(int id) {
        Optional<HashtagEntity> foundEntity = hashtagRepository.findById(id);
        if (foundEntity.isPresent()) {
            return foundEntity.get();
        } else {
            throw new HashtagNotFoundException("Hashtag id not found: " + id);
        }
    }

    @Override
    public List<HashtagEntity> getHashtagsForPost(int postId) {
        Optional<PostEntity> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return hashtagRepository.findByPost(post.get());
        } else {
            throw new PostNotFoundException("Post id not found: " + postId);
        }
    }

    @Override
    public HashtagEntity save(HashtagEntity hashtagEntity) {
        return hashtagRepository.save(hashtagEntity);
    }

    @Override
    public void deleteById(int id) {
        hashtagRepository.deleteById(id);
    }
}

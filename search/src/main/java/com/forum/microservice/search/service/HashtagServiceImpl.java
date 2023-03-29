package com.forum.microservice.search.service;

import com.forum.microservice.search.entity.HashtagEntity;
import com.forum.microservice.search.entity.PostEntity;
//import com.forum.microservice.search.entity.UserEntity;
import com.forum.microservice.search.exceptions.HashtagNotFoundException;
import com.forum.microservice.search.exceptions.PostNotFoundException;
//import com.forum.microservice.search.exceptions.UserNotFoundException;
import com.forum.microservice.search.repository.HashtagRepository;
import com.forum.microservice.search.repository.PostRepository;
//import com.forum.microservice.search.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HashtagServiceImpl implements HashtagService {
    private HashtagRepository hashtagRepository;
//    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public HashtagServiceImpl(
            HashtagRepository hashtagRepository,
 //           UserRepository userRepository,
            PostRepository postRepository) {
        this.hashtagRepository = hashtagRepository;
 //       this.userRepository = userRepository;
        this.postRepository = postRepository;
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

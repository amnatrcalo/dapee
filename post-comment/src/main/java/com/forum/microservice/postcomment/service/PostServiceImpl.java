package com.forum.microservice.postcomment.service;

import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.SubforumEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import com.forum.microservice.postcomment.exceptions.PostNotFoundException;
import com.forum.microservice.postcomment.exceptions.SubforumNotFoundException;
import com.forum.microservice.postcomment.exceptions.UserNotFoundException;
import com.forum.microservice.postcomment.repository.PostRepository;
import com.forum.microservice.postcomment.repository.SubforumRepository;
import com.forum.microservice.postcomment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
  private PostRepository postRepository;
  private UserRepository userRepository;

  private SubforumRepository subforumRepository;

  @Autowired
  public PostServiceImpl(
      PostRepository postRepository,
      UserRepository userRepository,
      SubforumRepository subforumRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
    this.subforumRepository = subforumRepository;
  }

  @Override
  public List<PostEntity> findAll() {
    return postRepository.findAll();
  }

  @Override
  public PostEntity findById(int id) {
    Optional<PostEntity> foundEntity = postRepository.findById(id);
    if (foundEntity.isPresent()) {
      return foundEntity.get();
    } else {
      throw new PostNotFoundException("Post id not found: " + id);
    }
  }

  @Override
  public List<PostEntity> getPostsForUser(int userId) {
    Optional<UserEntity> user = userRepository.findById(userId);
    if (user.isPresent()) {
      return postRepository.findByCreator(user.get());
    } else {
      throw new UserNotFoundException("User id not found: " + userId);
    }
  }

  @Override
  public List<PostEntity> getPostsOfSubforum(int subforumId) {

    Optional<SubforumEntity> subforum = subforumRepository.findById(subforumId);
    if (subforum.isPresent()) {
      return postRepository.findBySubforum(subforum.get());
    } else {
      throw new SubforumNotFoundException("Subforum id not found: " + subforumId);
    }
  }

  @Override
  public PostEntity save(PostEntity postEntity) {
    return postRepository.save(postEntity);
  }

  @Override
  public void deleteById(int id) {
    postRepository.deleteById(id);
  }
}

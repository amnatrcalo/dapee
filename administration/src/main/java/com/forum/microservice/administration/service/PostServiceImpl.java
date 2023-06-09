package com.forum.microservice.administration.service;

import com.forum.microservice.administration.entity.PostEntity;
import com.forum.microservice.administration.entity.SubforumEntity;
import com.forum.microservice.administration.entity.UserEntity;
import com.forum.microservice.administration.exceptions.PostNotFoundException;
import com.forum.microservice.administration.exceptions.SubforumNotFoundException;
import com.forum.microservice.administration.exceptions.UserNotFoundException;
import com.forum.microservice.administration.repository.PostRepository;
import com.forum.microservice.administration.repository.SubforumRepository;
import com.forum.microservice.administration.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  @Override
  public List<PostEntity> getPosts() {
    return postRepository.getPosts();
  }

  @Override
  public PostEntity find(int id) {
    Optional<PostEntity> foundEntity = postRepository.find(id);
    if (foundEntity.isPresent()) {
      return foundEntity.get();
    } else {
      throw new PostNotFoundException("Post id not found: " + id);
    }
  }

  @Override
  public void softDelete(PostEntity post) {
    post.setDeleted(true);
    postRepository.save(post);
  }
}

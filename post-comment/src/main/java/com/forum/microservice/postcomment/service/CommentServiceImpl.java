package com.forum.microservice.postcomment.service;

import com.forum.microservice.postcomment.entity.CommentEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import com.forum.microservice.postcomment.exceptions.CommentNotFoundException;
import com.forum.microservice.postcomment.exceptions.PostNotFoundException;
import com.forum.microservice.postcomment.exceptions.UserNotFoundException;
import com.forum.microservice.postcomment.repository.CommentRepository;
import com.forum.microservice.postcomment.repository.PostRepository;
import com.forum.microservice.postcomment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
  private CommentRepository commentRepository;
  private UserRepository userRepository;
  private PostRepository postRepository;

  @Autowired
  public CommentServiceImpl(
      CommentRepository commentRepository,
      UserRepository userRepository,
      PostRepository postRepository) {
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.postRepository = postRepository;
  }

  @Override
  public List<CommentEntity> findAll() {
    return commentRepository.findAll();
  }

  @Override
  public CommentEntity findById(int id) {
    Optional<CommentEntity> foundEntity = commentRepository.findById(id);
    if (foundEntity.isPresent()) {
      return foundEntity.get();
    } else {
      throw new CommentNotFoundException("Comment id not found: " + id);
    }
  }

  @Override
  public List<CommentEntity> getCommentsForUser(int userId) {
    Optional<UserEntity> user = userRepository.findById(userId);
    if (user.isPresent()) {
      return commentRepository.findByCreator(user.get());
    } else {
      throw new UserNotFoundException("User id not found: " + userId);
    }
  }

  @Override
  public List<CommentEntity> getCommentsForPost(int postId) {
    Optional<PostEntity> post = postRepository.findById(postId);
    if (post.isPresent()) {
      return commentRepository.findByPost(post.get());
    } else {
      throw new PostNotFoundException("Post id not found: " + postId);
    }
  }

  @Override
  public List<CommentEntity> getByRootComment(int commentId) {
    Optional<CommentEntity> root = commentRepository.findById(commentId);
    if (root.isPresent()) {
      return commentRepository.findByRootComment(root.get());
    } else {
      throw new CommentNotFoundException("Comment id not found: " + commentId);
    }
  }

  @Override
  public CommentEntity save(CommentEntity commentEntity) {
    return commentRepository.save(commentEntity);
  }

  @Override
  public void deleteById(int id) {
    commentRepository.deleteById(id);
  }
}

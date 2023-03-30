package com.forum.microservice.postcomment.service;

import com.forum.microservice.postcomment.entity.CommentEntity;
import com.forum.microservice.postcomment.entity.LikeEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import com.forum.microservice.postcomment.exceptions.CommentNotFoundException;
import com.forum.microservice.postcomment.exceptions.LikeNotFoundException;
import com.forum.microservice.postcomment.exceptions.PostNotFoundException;
import com.forum.microservice.postcomment.exceptions.UserNotFoundException;
import com.forum.microservice.postcomment.repository.CommentRepository;
import com.forum.microservice.postcomment.repository.LikeRepository;
import com.forum.microservice.postcomment.repository.PostRepository;
import com.forum.microservice.postcomment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @Autowired
    public LikeServiceImpl(
            LikeRepository likeRepository,
            UserRepository userRepository,
            PostRepository postRepository,
            CommentRepository commentRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<LikeEntity> findAll() {
        return likeRepository.findAll();
    }

    @Override
    public LikeEntity findById(int id) {
        Optional<LikeEntity> foundEntity = likeRepository.findById(id);
        if (foundEntity.isPresent()) {
            return foundEntity.get();
        } else {
            throw new LikeNotFoundException("Like id not found: " + id);
        }
    }

    @Override
    public List<LikeEntity> getLikesForUser(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return likeRepository.findByVoter(user.get());
        } else {
            throw new UserNotFoundException("User id not found: " + userId);
        }
    }

    @Override
    public List<LikeEntity> getLikesForPost(int postId) {
        Optional<PostEntity> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return likeRepository.findByPost(post.get());
        } else {
            throw new PostNotFoundException("Post id not found: " + postId);
        }
    }
    @Override
    public List<LikeEntity> getLikesForComment(int commentId) {
        Optional<CommentEntity> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            return likeRepository.findByComment(comment.get());
        } else {
            throw new CommentNotFoundException("Comment id not found: " + commentId);
        }
    }

    @Override
    public LikeEntity save(LikeEntity likeEntity) {
        return likeRepository.save(likeEntity);
    }

    @Override
    public void deleteById(int id) {
        likeRepository.deleteById(id);
    }
}

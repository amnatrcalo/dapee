package com.forum.microservice.postcomment.controller;

import com.forum.microservice.postcomment.entity.LikeEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.entity.UserEntity;
import com.forum.microservice.postcomment.exceptions.LikeNotFoundException;
import com.forum.microservice.postcomment.model.Like;
import com.forum.microservice.postcomment.service.LikeService;
import com.forum.microservice.postcomment.service.PostService;
import com.forum.microservice.postcomment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-com")
public class LikeController {
    private LikeService likeService;
    private UserService userService;
    private PostService postService;

    @Autowired
    public LikeController(LikeService likeService, UserService userService, PostService postService) {
        this.likeService = likeService;
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/likes")
    public List<LikeEntity> findAll() {
        return likeService.findAll();
    }

    @GetMapping("/likes/{likeId}")
    public LikeEntity getLike(@PathVariable int likeId) {
        LikeEntity like = likeService.findById(likeId);

        if (like == null) {
            throw new LikeNotFoundException("Like id not found: " + likeId);
        }

        return like;
    }
    @PostMapping("/likes")
    public LikeEntity addLike(@RequestBody Like like) {
       LikeEntity likeEntity = new LikeEntity();
        UserEntity user = userService.findById(like.getVoterId());
       likeEntity.setVoter(user);
        PostEntity post = postService.findById(like.getPostId());
        likeEntity.setPost(post);
        return likeService.save(likeEntity);
    }
    @GetMapping("/likes-for-user/{userId}")
    public List<LikeEntity> getLikesForUser(@PathVariable int userId) {
        return likeService.getLikesForUser(userId);
    }

    @GetMapping("/likes-for-post/{postId}")
    public List<LikeEntity> getLikesForPost(@PathVariable int postId) {
        return likeService.getLikesForPost(postId);
    }

    @GetMapping("/likes-for-comment/{commentId}")
    public List<LikeEntity> getLikesForComment(@PathVariable int commentId) {
        return likeService.getLikesForComment(commentId);
    }

    @DeleteMapping("/likes/{likeId}")
    public void deleteLike(@PathVariable int likeId) {
        LikeEntity tempLike = likeService.findById(likeId);

        if (tempLike == null) {
            throw new LikeNotFoundException("Like id not found: " + likeId);
        }

        likeService.deleteById(likeId);
    }
}

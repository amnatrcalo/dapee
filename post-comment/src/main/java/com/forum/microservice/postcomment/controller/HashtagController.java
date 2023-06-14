package com.forum.microservice.postcomment.controller;

import com.forum.microservice.postcomment.entity.HashtagEntity;
import com.forum.microservice.postcomment.entity.PostEntity;
import com.forum.microservice.postcomment.exceptions.HashtagNotFoundException;
import com.forum.microservice.postcomment.model.Hashtag;
import com.forum.microservice.postcomment.service.HashtagService;
import com.forum.microservice.postcomment.service.PostService;
import com.forum.microservice.postcomment.service.SubforumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-com")
public class HashtagController {
    private HashtagService hashtagService;
    private PostService postService;

    @Autowired
    public HashtagController(HashtagService hashtagService, PostService postService) {
        this.hashtagService = hashtagService;
        this.postService = postService;
    }

    @GetMapping("/hashtags")
    public List<HashtagEntity> findAll() {
        return hashtagService.findAll();
    }

    @GetMapping("/hashtags/{hashtagId}")
    public HashtagEntity getHashtag(@PathVariable int hashtagId) {
        HashtagEntity hashtag = hashtagService.findById(hashtagId);

        if (hashtag == null) {
            throw new HashtagNotFoundException("Hashtag id not found: " + hashtagId);
        }

        return hashtag;
    }
    @PostMapping("/hashtags")
    public HashtagEntity addHashtag(@RequestBody Hashtag hashtag) {

        HashtagEntity hashtagEntity = new HashtagEntity();
        hashtagEntity.setName(hashtag.getName());
        PostEntity post = postService.findById(hashtag.getPostId());
        hashtagEntity.setPost(post);

        return hashtagService.save(hashtagEntity);
    }
    @GetMapping("/hashtags-for-post/{postId}")
    public List<HashtagEntity> getHashtagsForPost(@PathVariable int postId) {
        return hashtagService.getHashtagsForPost(postId);
    }

    @DeleteMapping("/hashtags/{hashtagId}")
    public void deleteLike(@PathVariable int hashtagId) {
        HashtagEntity tempHash = hashtagService.findById(hashtagId);

        if (tempHash == null) {
            throw new HashtagNotFoundException("Hashtag id not found: " + hashtagId);
        }

        hashtagService.deleteById(hashtagId);
    }
}

package com.forum.microservice.search.controller;

import com.forum.microservice.search.entity.HashtagEntity;
import com.forum.microservice.search.exceptions.HashtagNotFoundException;
import com.forum.microservice.search.service.HashtagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class HashtagController {
    private HashtagService hashtagService;

    @Autowired
    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
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
    public HashtagEntity addHashtag(@RequestBody HashtagEntity hashtag) {
        hashtag.setId(0);
        return hashtagService.save(hashtag);
    }

    @PutMapping("/hashtags")
    public HashtagEntity updateHashtag(@RequestBody HashtagEntity hashtag) {
        return hashtagService.save(hashtag);
    }

    @GetMapping("hashtags-for-post/{postId}")
    public List<HashtagEntity> getHashtagsForPost(@PathVariable int postId) {
        return hashtagService.getHashtagsForPost(postId);
    }

    @DeleteMapping("/hashtags/{hashtagId}")
    public void deleteHashtag(@PathVariable int hashtagId) {
        HashtagEntity tempHashtag = hashtagService.findById(hashtagId);

        if (tempHashtag == null) {
            throw new HashtagNotFoundException("Hashtag id not found: " + hashtagId);
        }

        hashtagService.deleteById(hashtagId);
    }
}

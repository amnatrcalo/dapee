package com.forum.microservice.search.controller;

import com.forum.microservice.search.entity.SubforumEntity;
import com.forum.microservice.search.exceptions.SubforumNotFoundException;
import com.forum.microservice.search.service.SubforumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SubforumController {
    private SubforumService subforumService;

    @Autowired
    public SubforumController(SubforumService subforumService) {
        this.subforumService = subforumService;
    }

    @GetMapping("/subforums")
    public List<SubforumEntity> findAll() {
        return subforumService.findAll();
    }

    @GetMapping("/subforums/{subforumId}")
    public SubforumEntity getUser(@PathVariable int subforumId) {

        SubforumEntity subforum = subforumService.findById(subforumId);

        if (subforum == null) {
            throw new SubforumNotFoundException("Subforum id not found: " + subforumId);
        }

        return subforum;
    }

    @PostMapping("/subforums")
    public SubforumEntity addSubforum(@RequestBody SubforumEntity subforum) {
        subforum.setId(0);
        return subforumService.save(subforum);
    }

    @PutMapping("/subforums")
    public SubforumEntity updateSubforum(@RequestBody SubforumEntity subforum) {
        return subforumService.save(subforum);
    }

    @GetMapping("/subforums/substring/{name}")
    public SubforumEntity getSubforumContainsSubstring(@PathVariable String name) {
        return subforumService.getSubforumBySubstring(name);
    }

    @DeleteMapping("/subforums/{subforumId}")
    public void deleteUser(@PathVariable int subforumId) {
        SubforumEntity tempSubforum = subforumService.findById(subforumId);

        if (tempSubforum == null) {
            throw new SubforumNotFoundException("Subforum id not found: " + subforumId);
        }

        subforumService.deleteById(subforumId);
    }
}

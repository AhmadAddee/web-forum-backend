package com.example.webforum.webservice;

import com.example.webforum.business.IPostService;
import com.example.webforum.business.bo.Message;
import com.example.webforum.business.bo.Post;
import com.example.webforum.business.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    @Autowired
    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    //@RequestMapping(path = "/posts", method = RequestMethod.GET)
    @GetMapping("/get-all")
    public List<Post> getPosts() {
        return this.postService.getPosts();
    }

    @GetMapping("/get-posts")
    public List<Post> getPostByUsername(@RequestParam(value = "creator", required = false)String username) {
        return this.postService.getPostByCreator(username);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody Post post) {
        return this.postService.createPost(post);
    }

}

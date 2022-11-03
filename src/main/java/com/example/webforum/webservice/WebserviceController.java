package com.example.webforum.webservice;

import com.example.webforum.business.Post;
import com.example.webforum.business.PostService;
import com.example.webforum.business.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    //private final DateUtils dateUtils;
    private final PostService postService;

    public WebserviceController(PostService postService) {
        this.postService = postService;
    }

    //@RequestMapping(path = "/posts", method = RequestMethod.GET)
    @GetMapping("/posts")
    public List<Post> getPosts() {
        return this.postService.getPostsForUser();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.postService.getUsers();
    }

    @GetMapping("/user")
    public User getUserByUsername(@RequestParam(value = "username", required = false)String username) {
        return this.postService.getUserByUsername(username);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPost(@RequestBody Post post) {
        this.postService.addPost(post);
    }
}

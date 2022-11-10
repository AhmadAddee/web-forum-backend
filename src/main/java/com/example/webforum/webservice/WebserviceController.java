package com.example.webforum.webservice;

import com.example.webforum.business.IPostService;
import com.example.webforum.business.Post;
import com.example.webforum.business.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    //private final DateUtils dateUtils;
    @Autowired
    private final IPostService postService;

    public WebserviceController(IPostService postService) {
        this.postService = postService;
    }

    //@RequestMapping(path = "/posts", method = RequestMethod.GET)
    @GetMapping("/posts")
    public List<Post> getPosts() {
        return this.postService.getPosts();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.postService.getUsers();
    }

    @GetMapping("/post")
    public List<Post> getPostByUsername(@RequestParam(value = "creator", required = false)String username) {
        return this.postService.getPostByCreator(username);
    }

    @GetMapping("/user")
    public User getUserByUsername(@RequestParam(value = "username", required = false)String username) {
        return this.postService.getUserByUsername(username);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPost(@RequestBody Post post) {
        return this.postService.createPost(post);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddUser(@RequestBody User user) {
        return this.postService.addUser(user);
    }
}

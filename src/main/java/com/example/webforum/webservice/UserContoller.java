package com.example.webforum.webservice;

import com.example.webforum.business.IUserService;
import com.example.webforum.business.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserContoller {
    @Autowired
    private final IUserService iUserService;

    public UserContoller(IUserService postService) {
        this.iUserService = postService;
    }

    @GetMapping("/get-all")
    public List<User> getUsers() {
        return this.iUserService.getUsers();
    }

    @GetMapping("/get-user")
    public User getUserByUsername(@RequestParam(value = "username", required = false)String username) {
        return this.iUserService.getUserByUsername(username);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddUser(@RequestBody User user) {
        return this.iUserService.addUser(user);
    }

    @PostMapping("/login")
    //@ResponseStatus(HttpStatus.CREATED)
    public User Login(@RequestBody User user) {
        System.out.println(user);
        return this.iUserService.logIn(user);
    }

}

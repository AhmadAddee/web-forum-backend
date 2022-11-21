package com.example.webforum.business.services;

import com.example.webforum.business.IUserService;
import com.example.webforum.business.bo.Post;
import com.example.webforum.business.bo.User;
import com.example.webforum.db.dbo.PostDb;
import com.example.webforum.db.dbo.UserDb;
import com.example.webforum.db.repositories.UserRepository;
import com.example.webforum.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        Iterable<UserDb> userDbs = this.userRepository.findAll();
        List<User> userList = new ArrayList<>();
        userDbs.forEach(userDb -> userList.add(userDbToUser(userDb)));
        return userList;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null)
            throw new RuntimeException("Post cannot be null");
        UserDb userDb = this.userRepository.getByUsername(username);
        return userDb != null? userDbToUser(userDb) : null;
    }

    @Override
    public String addUser(User user) {
        if (user.getUsername() == null || user.getFullName() == null ||
            user.getAge() == 0 || user.getPassword() == null)
            return null;
        else if (this.userRepository.getByUsername(user.getUsername()) != null)
            return null;
        UserDb userDb = new UserDb();
        userDb.setUsername(user.getUsername());
        userDb.setFull_name(user.getFullName());
        userDb.setAge(user.getAge());
        userDb.setPassword(user.getPassword());
        userDb.setPostDbList(new ArrayList<>());
        this.userRepository.save(userDb);
        return user.getUsername();
    }

    @Override
    public User logIn(User user) {
        if (user == null)
            throw new RuntimeException("Post cannot be null");
        UserDb userDb = this.userRepository.getByUsername(user.getUsername());
        return userDb == null? null : userDb.getPassword().equals(user.getPassword())? userDbToUser(userDb) : null;
    }

    private User userDbToUser(UserDb userDb) {
        User user = new User();
        user.setUsername(userDb.getUsername());
        user.setFullName(userDb.getFull_name());
        user.setAge(userDb.getAge());
        user.setPassword(userDb.getPassword());
        user.setPostList(new ArrayList<>());
        userDb.getPostDbList().forEach(postDb -> user.getPostList().add(postDbToPost(postDb)));
        return user;
    }

    private Post postDbToPost(PostDb postDb) {
        Post post =  new Post();
        post.setId(postDb.getId());
        post.setCreator(postDb.getCreator().getUsername());
        post.setContent(postDb.getContent());
        Date date = new Date(postDb.getCreatedDate().getTime());
        post.setCreatedDate(date);
        post.setTimeAgo(DateUtils.calculateTimeAgo(post.getCreatedDate()));
        return post;
    }
}

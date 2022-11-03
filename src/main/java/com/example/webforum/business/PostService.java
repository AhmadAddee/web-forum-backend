package com.example.webforum.business;

import com.example.webforum.db.dbo.PostDb;
import com.example.webforum.db.dbo.UserDb;
import com.example.webforum.db.repositories.PostRepository;
import com.example.webforum.db.repositories.UserRepository;
import com.example.webforum.util.DateUtils;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public List<Post> getPostsForUser(){
        Iterable<PostDb> postDbs = this.postRepository.findAll();
        List<Post> postList = new ArrayList<>();
        postDbs.forEach(postDb -> {
            Post post = new Post();
            post.setPostId(postDb.getId());
            //post.setCreatorName(postDb.getCreator());
            post.setPostContent(postDb.getContent());
            post.setCreatedDate(postDb.getCreatedDate());
            postList.add( post);
        });
        return postList;
    }

    public List<User> getUsers() {
        Iterable<UserDb> userDbs = this.userRepository.findAll();
        List<User> users = new ArrayList<>();
        userDbs.forEach(userDbn -> {
            User user = new User();
            user.setUsername(userDbn.getUsername());
            user.setFullName(userDbn.getFull_name());
            user.setAge(userDbn.getAge());
            users.add(user);
        });
        return users;
    }

    public User getUserByUsername(String username) {
        if (username == null)
            throw new RuntimeException("Post cannot be null");
        UserDb userDb = this.userRepository.getByUsername(username);
        User user = new User();
        user.setAge(userDb.getAge());
        user.setUsername(userDb.getUsername());
        user.setFullName(userDb.getFull_name());
        return user;
    }

    public void addPost(Post post) {
        if (post == null)
            throw new RuntimeException("Post cannot be null");
        PostDb postDb = new PostDb();
        postDb.setContent(post.getPostContent());
        postDb.setCreatedDate(Date.valueOf(LocalDate.now()));
        this.postRepository.save(postDb);
    }
}

package com.example.webforum.business;

import java.util.List;

public interface IPostService {
    List<Post> getPosts();
    List<User> getUsers();
    User getUserByUsername(String username);
    String createPost(Post post);
    String addUser(User user);
    List<Post> getPostByCreator(String username);
}

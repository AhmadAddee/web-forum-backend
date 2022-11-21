package com.example.webforum.business;

import com.example.webforum.business.bo.Post;

import java.util.List;

public interface IPostService {
    List<Post> getPosts();
    String createPost(Post post);
    List<Post> getPostByCreator(String username);
}

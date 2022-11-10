package com.example.webforum.business;

import com.example.webforum.business.bo.Message;
import com.example.webforum.business.bo.Post;
import com.example.webforum.business.bo.User;

import java.util.List;

public interface IPostService {
    List<Post> getPosts();
    List<User> getUsers();
    User getUserByUsername(String username);
    String createPost(Post post);
    String addUser(User user);
    List<Post> getPostByCreator(String username);
    String sendMessage(Message message);
    Message getMessageById(int id);
    List<Message> findMessageByReceiver(String receiver);
}

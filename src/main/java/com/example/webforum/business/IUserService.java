package com.example.webforum.business;

import com.example.webforum.business.bo.User;

import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User getUserByUsername(String username);
    String addUser(User user);
    User logIn(User user);
}

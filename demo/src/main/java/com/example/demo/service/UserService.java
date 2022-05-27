package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    int saveUser(User user);

    int updateUser(long id, User newUser);

    int deleteUser(long id);
}

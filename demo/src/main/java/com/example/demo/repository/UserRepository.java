package com.example.demo.repository;

import com.example.demo.model.User;
import java.util.List;

public interface UserRepository {

    User findById(Long id);

    List<User> findAll();

    int save(User user);

    int update(User user, long id);

    int delete(Long id);


}

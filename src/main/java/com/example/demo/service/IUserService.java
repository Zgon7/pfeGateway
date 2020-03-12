package com.example.demo.service;


import com.example.demo.model.User;

import java.util.List;

public interface IUserService {
    User save(User user);
    List<User> findAll();
    User update(User user);
    void delete(Long id);
    User findById(Long id);
}

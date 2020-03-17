package com.example.demo.service;


import com.example.demo.model.User;
import com.example.demo.model.UserDTO;

import java.util.List;

public interface IUserService {
    User save(UserDTO user);
    List<User> findAll();
    User update(User user);
    void delete(Long id);
    User findById(Long id);
}

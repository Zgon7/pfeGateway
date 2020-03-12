package com.example.demo.service;




import com.example.demo.model.Role;

import java.util.List;

public interface IRoleService {
    Role save(Role role);
    List<Role> findAll();
    Role update(Role role);
    void delete(Long id);
}

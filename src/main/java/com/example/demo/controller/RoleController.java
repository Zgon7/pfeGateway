package com.example.demo.controller;


import com.example.demo.model.Role;
import com.example.demo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @GetMapping("/roles")
    public List<Role> getAll() {

        return roleService.findAll();

    }

    @PostMapping("/role/add")
    public Role addRole(@RequestBody Role role) {
        return roleService.save(role);
    }

    @DeleteMapping("/role/delete/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }



    @PutMapping("/role/update/{id}")
    public Role updateRole (@PathVariable Long id, @RequestBody Role role)

    {
        role.setId(id);
        return roleService.update(role);
    }



}

package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "USER_ID") })
    private List<User> users = new ArrayList<>();

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}


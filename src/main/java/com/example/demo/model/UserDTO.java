package com.example.demo.model;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String cin;
    private String password;
    private String nom;
    private String prenom;
    private List<String> roles;

    public UserDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO(Long id, String username, String email, String cin, String password, String nom, String prenom, List<String> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.cin = cin;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.roles = roles;
    }

    public UserDTO(User u) {
        id = u.getId();
        username = u.getUsername();
        email = u.getEmail();
        cin = u.getCin();
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}

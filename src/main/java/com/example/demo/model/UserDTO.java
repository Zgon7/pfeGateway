package com.example.demo.model;

public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String cin;
    private String nom;
    private String prenom;

    public UserDTO() {
    }

    public UserDTO(Long id, String username, String email, String cin, String nom, String prenom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
    }

    public UserDTO(User u) {
        id = u.getId();
        username = u.getUsername();
        email = u.getEmail();
        cin = u.getCin();
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

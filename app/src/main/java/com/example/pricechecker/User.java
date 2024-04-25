package com.example.pricechecker;

public class User {
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String gender;
    private String birthdate;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String password, String fullName, String gender, String birthdate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
        this.birthdate = birthdate;

    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthdate;
    }
}
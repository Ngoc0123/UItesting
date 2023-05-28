package com.example.oop_ui_test.Classes;

public abstract class Account {
    private String username;
    private String password;

    //constructor
    public Account(){
        username = null;
        password = null;
    };

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.sherry.campus.model;

/**
 * Created by HP on 25-03-2018.
 */

public class User {

    public String name;
    public String email;
    public String password;

    public User(){

    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
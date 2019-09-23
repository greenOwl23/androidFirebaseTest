package com.example.firedb3;

import com.google.firebase.database.IgnoreExtraProperties;

import java.lang.reflect.Array;

@IgnoreExtraProperties
public class User {
//    public String username;
    public String email;
    public double balance;
    public Transaction transaction[];
    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email) {
        this.email = email;
        this.balance = 0;
    }



}

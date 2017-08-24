package com.javaconsumers.festpay.database;

/**
 * Created by AoD Akitektuo on 11-Jul-17 at 21:07.
 */

public class User {

    private int id;
    private String email;
    private String password;

    User(int id, String email, String password) {
        setId(id);
        setEmail(email);
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

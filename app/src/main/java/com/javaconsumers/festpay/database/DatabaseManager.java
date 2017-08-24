package com.javaconsumers.festpay.database;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AoD Akitektuo on 11-Jul-17 at 20:32.
 */

public class DatabaseManager {

    private DatabaseHelper database;

    public DatabaseManager(Context context) {
        setDatabase(new DatabaseHelper(context));
    }

    public boolean registerUser(String email, String emailConfirm, String password, String passwordConfirm) {
        if (email.isEmpty() || emailConfirm.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            return false;
        }
        if (!(email.equals(emailConfirm) && password.equals(passwordConfirm))) {
            return false;
        }
        if (!email.contains("@") || password.length() < 4) {
            return false;
        }
        Cursor cursor = getDatabase().getUsers();
        if (cursor.moveToFirst()) {
            do {
                if (email.equals(cursor.getString(DatabaseContract.CURSOR_EMAIL))) {
                    cursor.close();
                    return false;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        getDatabase().addUser(email, password);
        return true;
    }

    public DatabaseHelper getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseHelper database) {
        this.database = database;
    }

    public boolean loginUser(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return false;
        }
        Cursor cursor = getDatabase().getUsers();
        if (cursor.moveToFirst()) {
            do {
                if (email.equals(cursor.getString(DatabaseContract.CURSOR_EMAIL)) && password.equals(cursor.getString(DatabaseContract.CURSOR_PASSWORD))) {
                    cursor.close();
                    return true;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return false;
    }

    public boolean updateUser(String email, String passwordOld, String passwordNew, String passwordNewConfirm) {
        if (email.isEmpty() || passwordOld.isEmpty() || passwordNew.isEmpty() || passwordNewConfirm.isEmpty()) {
            return false;
        }
        if (!loginUser(email, passwordOld)) {
            return false;
        }
        if (!passwordNew.equals(passwordNewConfirm)) {
            return false;
        }
        Cursor cursor = getDatabase().getUserForEmail(email);
        if (cursor.moveToFirst()) {
            User user = new User(cursor.getInt(DatabaseContract.CURSOR_ID), cursor.getString(DatabaseContract.CURSOR_EMAIL), cursor.getString(DatabaseContract.CURSOR_PASSWORD));
            user.setPassword(passwordNew);
            getDatabase().updateUser(user);
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public List<User> searchEmail(String email) {
        Cursor cursor = getDatabase().getUserForSearch(email);
        List<User> users = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                users.add(new User(cursor.getInt(DatabaseContract.CURSOR_ID), cursor.getString(DatabaseContract.CURSOR_EMAIL), cursor.getString(DatabaseContract.CURSOR_PASSWORD)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return users;
    }
}

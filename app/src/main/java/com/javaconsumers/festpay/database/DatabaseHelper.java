package com.javaconsumers.festpay.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.javaconsumers.festpay.database.DatabaseContract.CURSOR_ID;

/**
 * Created by AoD Akitektuo on 11-Jul-17 at 20:08.
 */

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database.db";

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_QUERY = "CREATE TABLE " + DatabaseContract.UserContractEntry.TABLE_NAME + " (" +
            DatabaseContract.UserContractEntry.COLUMN_ID + " NUMBER," +
            DatabaseContract.UserContractEntry.COLUMN_EMAIL + " TEXT," +
            DatabaseContract.UserContractEntry.COLUMN_PASSWORD + " TEXT);";

    // Create table User (Id number, Email text, Password text);

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
    }

    void addUser(String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_ID, getGenerateId());
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_EMAIL, email);
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_PASSWORD, password);
        getWritableDatabase().insert(DatabaseContract.UserContractEntry.TABLE_NAME, null, contentValues);
    }

    Cursor getUsers() {
        String[] list = {DatabaseContract.UserContractEntry.COLUMN_ID,
                DatabaseContract.UserContractEntry.COLUMN_EMAIL,
                DatabaseContract.UserContractEntry.COLUMN_PASSWORD};
        return getReadableDatabase().query(DatabaseContract.UserContractEntry.TABLE_NAME, list, null, null, null, null, null);
    }

    private int getGenerateId() {
        int id = 0;
        Cursor cursor = getUsers();
        if (cursor.moveToLast()) {
            id = cursor.getInt(CURSOR_ID);
        }
        cursor.close();
        return ++id;
    }

    void updateUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_ID, user.getId());
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_EMAIL, user.getEmail());
        contentValues.put(DatabaseContract.UserContractEntry.COLUMN_PASSWORD, user.getPassword());
        String selection = DatabaseContract.UserContractEntry.COLUMN_EMAIL + " LIKE ?";
        String[] selectionArgs = {user.getEmail()};
        getWritableDatabase().update(DatabaseContract.UserContractEntry.TABLE_NAME, contentValues, selection, selectionArgs);
    }

    Cursor getUserForEmail(String email) {
        String[] results = {DatabaseContract.UserContractEntry.COLUMN_ID,
                DatabaseContract.UserContractEntry.COLUMN_EMAIL,
                DatabaseContract.UserContractEntry.COLUMN_PASSWORD};
        String selection = DatabaseContract.UserContractEntry.COLUMN_EMAIL + " LIKE ?";
        String[] selectionArgs = {email};
        return getReadableDatabase().query(DatabaseContract.UserContractEntry.TABLE_NAME, results, selection, selectionArgs, null, null, null);
    }

    Cursor getUserForSearch(String email) {
        String[] results = {DatabaseContract.UserContractEntry.COLUMN_ID,
                DatabaseContract.UserContractEntry.COLUMN_EMAIL,
                DatabaseContract.UserContractEntry.COLUMN_PASSWORD};
        String selection = DatabaseContract.UserContractEntry.COLUMN_EMAIL + " LIKE ?";
        String[] selectionArgs = {"%" + email + "%"};
        return getReadableDatabase().query(DatabaseContract.UserContractEntry.TABLE_NAME, results, selection, selectionArgs, null, null, null);
    }

}

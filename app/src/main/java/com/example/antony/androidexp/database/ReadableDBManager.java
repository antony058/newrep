package com.example.antony.androidexp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.antony.androidexp.model.User;

import java.util.ArrayList;
import java.util.List;

public class ReadableDBManager implements DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private String[] tableColumns = {DBHelper.USER_NAME, DBHelper.USER_AGE,
            DBHelper.USER_BIRTH_DATE, DBHelper.USER_LOCALE};

    public ReadableDBManager(Context context, String dbName) {
        dbHelper = new DBHelper(context, dbName, null, DBHelper.DATABASE_VERSION);
        initDBReadableMode();
    }

    @Override
    public void initDBReadableMode() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
    }

    @Override
    public void initDBWritableMode() {

    }

    public List<User> getUsersList() {
        List<User> userList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(DBHelper.TABLE_NAME, tableColumns,
                null, null, null, null, null);

        if (!cursor.moveToFirst())
            return null;

        do {
            User user = new User();
            user.setUserName(cursor.getString(cursor.getColumnIndex(DBHelper.USER_NAME)));
            user.setUserAge(cursor.getInt(cursor.getColumnIndex(DBHelper.USER_AGE)));
            user.setUserBirthDate(cursor.getString(cursor.getColumnIndex(DBHelper.USER_BIRTH_DATE)));
            user.setUserLocale(cursor.getString(cursor.getColumnIndex(DBHelper.USER_LOCALE)));
            userList.add(user);
        } while (cursor.moveToNext());

        cursor.close();

        return userList;
    }
}

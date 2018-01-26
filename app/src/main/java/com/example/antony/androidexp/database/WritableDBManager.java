package com.example.antony.androidexp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class WritableDBManager implements DBManager {
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public WritableDBManager(Context context, String dbName) {
        dbHelper = new DBHelper(context, dbName, null, DBHelper.DATABASE_VERSION);
        initDBWritableMode();
    }

    @Override
    public void initDBWritableMode() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                sqLiteDatabase = dbHelper.getWritableDatabase();
            }
        });
        thread.start();
    }

    @Override
    public void initDBReadableMode() {

    }

    public void insertRow(String userName, int userAge, String userBirthDate, String userLocale) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.USER_NAME, userName);
        values.put(DBHelper.USER_AGE, userAge);
        values.put(DBHelper.USER_BIRTH_DATE, userBirthDate);
        values.put(DBHelper.USER_LOCALE, userLocale);
        sqLiteDatabase.insert(DBHelper.TABLE_NAME, null, values);
    }
}

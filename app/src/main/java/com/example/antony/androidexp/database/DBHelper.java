package com.example.antony.androidexp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper implements BaseColumns{
    public final static String DATABASE_NAME = "app_database.db";
    public final static int DATABASE_VERSION = 3;
    public final static String TABLE_NAME = "users";
    public final static String USER_NAME = "user_name";
    public final static String USER_AGE = "user_age";
    public final static String USER_BIRTH_DATE = "user_birth_date";
    public final static String USER_LOCALE = "user_locale";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " ("
                + BaseColumns._ID + " integer primary key autoincrement, "
                + USER_NAME + " text not null, " + USER_AGE + " integer not null, "
                + USER_BIRTH_DATE + " text not null, " + USER_LOCALE + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}

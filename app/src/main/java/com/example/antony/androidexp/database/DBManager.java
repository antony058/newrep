package com.example.antony.androidexp.database;

public interface DBManager {
    int DB_MODE_WRITEBLE = 1;
    int DB_MODE_READABLE = 2;

    void initDBWritableMode();

    void initDBReadableMode();
}

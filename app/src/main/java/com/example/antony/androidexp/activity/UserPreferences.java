package com.example.antony.androidexp.activity;

import android.preference.PreferenceActivity;
import android.os.Bundle;

import com.example.antony.androidexp.R;

public class UserPreferences extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_user_preferences);

        addPreferencesFromResource(R.xml.user_pref);
    }
}

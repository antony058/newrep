package com.example.antony.androidexp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.antony.androidexp.R;
import com.example.antony.androidexp.adapter.UsersListAdapter;
import com.example.antony.androidexp.database.DBHelper;
import com.example.antony.androidexp.database.ReadableDBManager;

public class UsersListActivity extends AppCompatActivity {
    private ListView listViewUsers;

    private ReadableDBManager readableDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        readableDBManager = new ReadableDBManager(UsersListActivity.this, DBHelper.DATABASE_NAME);

        listViewUsers = (ListView) findViewById(R.id.listViewUsers);

        UsersListAdapter adapter = new UsersListAdapter(
                UsersListActivity.this, readableDBManager.getUsersList());
        listViewUsers.setAdapter(adapter);

        /*Intent intent = new Intent()
                .setType("/*")
                .setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);*/


    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            Log.d("uri: ", selectedfile.toString());
            Toast.makeText(UsersListActivity.this, selectedfile.toString(), Toast.LENGTH_LONG).show();
        }
    }*/
}

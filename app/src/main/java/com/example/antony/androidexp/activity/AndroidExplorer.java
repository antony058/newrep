package com.example.antony.androidexp.activity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.antony.androidexp.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AndroidExplorer extends ListActivity {
    private List<String> item = null;
    private List<String> path = null;
    private String root;
    private TextView myPath;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_android_explorer);

        myPath = (TextView)findViewById(R.id.path);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            root = getApplicationContext().getDataDir().getAbsolutePath();
        }
        getDir(root);
    }

    private void getDir(String dirPath)
    {
        String path_text = getString(R.string.location) + dirPath;
        myPath.setText(path_text);
        item = new ArrayList<String>();
        path = new ArrayList<String>();

        File f = new File(dirPath);
        File[] files = f.listFiles();

        if(!dirPath.equals(root))
        {
            item.add(root);
            path.add(root);

            item.add("../");
            path.add(f.getParent());
        }

        for(int i=0; i < files.length; i++)
        {
            File file = files[i];
            path.add(file.getPath());
            Log.d("path: ", file.getPath());

            if(file.isDirectory())
                item.add(file.getName() + "/");
            else
                item.add(file.getName());
        }
        ArrayAdapter<String> fileList =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        setListAdapter(fileList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File file = new File(path.get(position));
        if (file.isDirectory())
        {
            if(file.canRead())
                getDir(path.get(position));
            else
            {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.en)
                        .setTitle("[" + file.getName() + "] folder can't be read!")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).show();
            }
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.en)
                    .setTitle("[" + file.getName() + "]")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).show();
        }
    }
}
package com.example.antony.androidexp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.antony.androidexp.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileManager extends AppCompatActivity {
    private EditText editTextFileName;
    private EditText editTextFileContent;
    private Button btnWriteFile;
    private Button btnReadFile;
    private TextView tvFileContent;

    private File file;
    private String fileName;
    private String fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);
        init_widgets();
    }

    private void init_widgets() {
        editTextFileName = (EditText) findViewById(R.id.editTextFileName);
        editTextFileContent = (EditText) findViewById(R.id.editTextFileContent);
        btnWriteFile = (Button) findViewById(R.id.btnWriteFile);
        btnReadFile = (Button) findViewById(R.id.btnReadFile);
        tvFileContent = (TextView) findViewById(R.id.tvContentOfFile);

        btnWriteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileName = editTextFileName.getText().toString();
                fileContent = editTextFileContent.getText().toString();

                if (fileName.isEmpty() || fileContent.isEmpty()) {
                    Toast.makeText(FileManager.this, "Заполните поля", Toast.LENGTH_SHORT).show();
                    return;
                }

                file = new File(fileName);

                try {
                    FileOutputStream outputStream = openFileOutput(fileName, MODE_PRIVATE);
                    outputStream.write(fileContent.getBytes());
                    outputStream.close();
                }  catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnReadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileName = editTextFileName.getText().toString();

                if (fileName.isEmpty()) {
                    Toast.makeText(FileManager.this, "Заполните имя файла", Toast.LENGTH_SHORT).show();
                    return;
                }

                file = new File(fileName);
                BufferedReader reader = null;
                StringBuilder builder = new StringBuilder();

                try {
                    FileInputStream inputStream = openFileInput(fileName);
                    reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                        builder.append("\r\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                tvFileContent.setText(builder.toString());
            }
        });
    }
}

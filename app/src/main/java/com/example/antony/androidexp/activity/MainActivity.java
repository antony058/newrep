package com.example.antony.androidexp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.antony.androidexp.BirthDateDialogFragment;
import com.example.antony.androidexp.database.DBHelper;
import com.example.antony.androidexp.R;
import com.example.antony.androidexp.database.WritableDBManager;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ImageView imgViewLanguageFlag;
    private EditText editTextUserName;
    private EditText editTextUserAge;
    private Button btnBirthDate;
    private Button btnAddUser;

    public final static int[] CONTEXT_MENU_ITEMS = {1, 2};
    public final static int CONTEXT_MENU_ITEMS_GROUP = 100;
    public final static String BIRTH_DATE_DIALOG = "birth_date_dialog_fragment";
    private Date date = new Date();
    private String default_language;

    private WritableDBManager writableDBManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        default_language = Locale.getDefault().getLanguage();

        checkPreferencesForLocale();

        init_widgets();
        writableDBManager = new WritableDBManager(this, DBHelper.DATABASE_NAME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemMenuShowUsers: {
                Intent intent = new Intent(MainActivity.this, UsersListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.itemMenuShowDirs : {
                startActivity(new Intent(MainActivity.this, AndroidExplorer.class));
                break;
            }
            case R.id.itemMenuWriteFile: {
                startActivity(new Intent(MainActivity.this, FileManager.class));
                break;
            }
            case R.id.itemMenuShowPreferences: {
                startActivity(new Intent(MainActivity.this, UserPreferences.class));
                break;
            }
            case R.id.itemMenuGraphicActivity: {
                startActivity(new Intent(MainActivity.this, GraphicActivity.class));
                break;
            }
            case R.id.itemMenuGraphicActivity2: {
                startActivity(new Intent(MainActivity.this, GraphicActivity2.class));
                break;
            }
            case R.id.itemMenuGraphicActivity3: {
                startActivity(new Intent(MainActivity.this, GraphicActivity3.class));
                break;
            }
            case R.id.itemMenuGraphicActivity4: {
                startActivity(new Intent(MainActivity.this, GraphicActivity4.class));
                break;
            }
            case R.id.itemMenuGraphicActivity5: {
                startActivity(new Intent(MainActivity.this, GraphicActivity5.class));
                break;
            }
            default: {
                break;
            }
        }
        return true;
    }

    private void init_widgets() {
        imgViewLanguageFlag = (ImageView) findViewById(R.id.imgViewLanguageFlagIcon);

        registerForContextMenu(imgViewLanguageFlag);

        editTextUserName = (EditText) findViewById(R.id.editTextName);
        editTextUserAge = (EditText) findViewById(R.id.editTextAge);

        btnBirthDate = (Button) findViewById(R.id.btnBirthDate);
        btnBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                BirthDateDialogFragment dialogFragment = BirthDateDialogFragment.initDate(date);
                dialogFragment.show(fm, BIRTH_DATE_DIALOG);
            }
        });

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addUserDataFromWidgetsToDatabase())
                    Toast.makeText(MainActivity.this, android.R.string.ok, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, android.R.string.no, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle(R.string.change_language);

        int i = 0;
        for (String item: getResources().getStringArray(R.array.languages)) {
            boolean isCurrentLang = false;

            if (default_language.equalsIgnoreCase(item))
                isCurrentLang = true;

            menu.add(CONTEXT_MENU_ITEMS_GROUP, CONTEXT_MENU_ITEMS[i], Menu.NONE,item)
                    .setChecked(isCurrentLang);
            i++;
        }
        menu.setGroupCheckable(CONTEXT_MENU_ITEMS_GROUP, true, false);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final int clicked_item_id = item.getItemId();
        for (int item_id: CONTEXT_MENU_ITEMS) {
            if (clicked_item_id == item_id) {
                String changed_locale = item.toString();
                changeLocale(changed_locale);
                changePreferencesLocale(changed_locale);
            }
    }
        /*Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intent);*/
        return super.onContextItemSelected(item);
    }

    public void changeLocale(String lang) {
        Locale locale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(locale);
        res.updateConfiguration(conf, dm);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkPreferencesForLocale() {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String keeped_locale = preferences.getString(getString(R.string.keeped_locale), "");
        if (keeped_locale.isEmpty()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(getString(R.string.keeped_locale), default_language);
            editor.apply();
        }
        else {
            if (!keeped_locale.equalsIgnoreCase(default_language)) {
                changeLocale(keeped_locale);
                changePreferencesLocale(keeped_locale);
            }
        }
    }

    public void changePreferencesLocale(String lang) {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(getString(R.string.keeped_locale), lang);
        editor.apply();
    }

    public void setBirthDate(Date date) {
        this.date = date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        String firstNullMonth = null;
        String firstNullDay = null;

        if (month < 10)
            firstNullMonth = "0";
        else
            firstNullMonth = "";

        if (day < 10)
            firstNullDay = "0";
        else
            firstNullDay = "";

        btnBirthDate.setText(firstNullDay + day + "-" + firstNullMonth + month + "-" + year);
    }

    private boolean addUserDataFromWidgetsToDatabase() {
        String userName = editTextUserName.getText().toString();
        String userAge = editTextUserAge.getText().toString();
        String userBirthDate = btnBirthDate.getText().toString();

        if (userName.isEmpty() || userAge.isEmpty() || userBirthDate.isEmpty()) {
            return false;
        }

        writableDBManager.insertRow(userName, Integer.parseInt(userAge),
                userBirthDate, default_language);

        return true;
    }
}

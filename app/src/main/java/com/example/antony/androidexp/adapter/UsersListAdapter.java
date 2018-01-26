package com.example.antony.androidexp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.antony.androidexp.R;
import com.example.antony.androidexp.model.User;

import java.util.List;
import java.util.Locale;

public class UsersListAdapter extends BaseAdapter {
    private List<User> userList;
    private LayoutInflater layoutInflater;
    private Context context;
    private String[] locales;

    public UsersListAdapter(Context context, List<User> users) {
        this.userList = users;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        locales = context.getResources().getStringArray(R.array.languages);
    }

    @Override
    public int getCount() {
        if (userList == null)
            return 0;

        return userList.size();
    }

    @Override
    public User getItem(int i) {
        return userList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View userView = view;

        if (userView == null) {
            userView = layoutInflater.inflate(R.layout.user_item, viewGroup, false);
        }

        User user = getItem(i);

        ((TextView) userView.findViewById(R.id.tvUserItemUserName)).setText(user.getUserName());
        String userAge = String.format(Locale.getDefault(), "%d %s", user.getUserAge(),
                context.getResources().getString(R.string.years_old));
        ((TextView) userView.findViewById(R.id.tvUserItemUserAge))
                .setText(userAge);
        ((TextView) userView.findViewById(R.id.tvUserItemUserBirthDate))
                .setText(user.getUserBirthDate());
        ((ImageView) userView.findViewById(R.id.imgViewUserItemFlagIcon))
                .setImageURI(Uri.parse("android.resource://com.example.antony.androidlabs/" +
                        "drawable/" + user.getUserLocale()));

        return userView;
    }
}

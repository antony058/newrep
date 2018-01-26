package com.example.antony.androidexp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.antony.androidexp.activity.MainActivity;

import java.util.Calendar;
import java.util.Date;

public class BirthDateDialogFragment extends DialogFragment {
    private DatePicker datePickerBirthDate;
    public final static String BUNDLE_DATE = "date";

    public static BirthDateDialogFragment initDate(Date d) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_DATE, d);

        BirthDateDialogFragment fragment = new BirthDateDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.datepicker_dialog, null);

        Date date = (Date) getArguments().getSerializable(BUNDLE_DATE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePickerBirthDate = (DatePicker) v.findViewById(R.id.datePickerBirthDate);
        datePickerBirthDate.init(year, month, day, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v);
        builder.setTitle(R.string.birth_date);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Calendar calendar = Calendar.getInstance();
                int day = datePickerBirthDate.getDayOfMonth();
                int month = datePickerBirthDate.getMonth();
                int year = datePickerBirthDate.getYear();
                calendar.set(year, month, day);
                ((MainActivity) getActivity()).setBirthDate(calendar.getTime());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);

        return builder.create();
    }
}

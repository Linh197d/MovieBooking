package com.base.mvvmbaseproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DialogInterface.OnClickListener {

    private final int style;
    private DatePicker mDatePicker;

    public DatePickerFragment(int xml) {
        style = xml;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = requireActivity().getLayoutInflater().inflate(style, null);
        Calendar calendar = Calendar.getInstance();
        mDatePicker = view.findViewById(R.id.datePicker);
        mDatePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                null);

        return new AlertDialog.Builder(requireActivity())
                .setView(view)
                .setPositiveButton("OK", this)
                .setNegativeButton("Thoát", this)
                .create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                mDatePicker.clearFocus();
                onDateSet(mDatePicker,
                        mDatePicker.getYear(),
                        mDatePicker.getMonth(),
                        mDatePicker.getDayOfMonth());
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                dialog.dismiss();
                break;
        }
    }

    public String onDateSet(View view, int year, int month, int day) {
        return day + " / " + month + " / " + year;
    }
}
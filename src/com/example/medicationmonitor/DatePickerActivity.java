package com.example.medicationmonitor;


import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class DatePickerActivity extends Activity implements OnClickListener {

    private Button mDateButton;
    private TextView setdate;
    private Calendar mCalen;
    private int day;
    private int month;
    private int year;

    private static final int DATE_PICKER_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.once);
        mDateButton = (Button) findViewById(R.id.adddate);
        setdate=(TextView)findViewById(R.id.setdate);
        mCalen = Calendar.getInstance();
        day = mCalen.get(Calendar.DAY_OF_MONTH);
        month = mCalen.get(Calendar.MONTH);
        year = mCalen.get(Calendar.YEAR);
        mDateButton.setOnClickListener(this);
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case DATE_PICKER_ID:
                return new DatePickerDialog(this, datePickerListener,
                        year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener =
            new DatePickerDialog.OnDateSetListener() {

                // while dialog box is closed, below method is called.
                public void onDateSet(DatePicker view, int selectedYear,
                        int selectedMonth, int selectedDay) {
                    year = selectedYear;
                    month = selectedMonth;
                    day = selectedDay;

                    // Set the Date String in Button
                    setdate.setText(day + " / " + (month + 1) + " / " + year);
                }
            };

    @Override
    public void onClick(View v) {
        showDialog(DATE_PICKER_ID);

    }
}
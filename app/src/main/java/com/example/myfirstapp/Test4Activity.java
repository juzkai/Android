package com.example.myfirstapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class Test4Activity extends AppCompatActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener{
    private Chronometer chronometer;
    private Dialog dialog = new Dialog();
    private DatePicker calendarDate;
    private DatePicker spinnerDate;
    private TimePicker clockTimer;
    private TimePicker spinnerTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
        init();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void init() {

        chronometer = (Chronometer)findViewById(R.id.chronometer);

        calendarDate = (DatePicker)findViewById(R.id.calendarDate);
        spinnerDate = (DatePicker)findViewById(R.id.spinnerDate);
        calendarDate.setOnDateChangedListener(this);
        spinnerDate.setOnDateChangedListener(this);

        clockTimer = (TimePicker)findViewById(R.id.clockTimer);
        spinnerTimer = (TimePicker)findViewById(R.id.spinnerTimer);
        clockTimer.setOnTimeChangedListener(this);
        spinnerTimer.setOnTimeChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.start:
                chronometer.start();
                break;
            case R.id.stop:
                chronometer.stop();
                break;
            case R.id.reset:
                chronometer.setBase(SystemClock.elapsedRealtime());
            default: break;
        }
    }
    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        dialog.showToast(this, year + "年" + month + "月" + day + "日");
        int pickerId = view.getId();
        switch (pickerId) {
            case R.id.calendarDate:
                spinnerDate.init(year, month, day, this);
                break;
            case R.id.spinnerDate:
                calendarDate.init(year, month, day, this);
                break;
            default: break;
        }
    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
        dialog.showToast(this, hour + "时" + minute + "分");
        int timerId = timePicker.getId();
        switch (timerId) {
            case R.id.clockTimer:
                spinnerTimer.setHour(hour);
                spinnerTimer.setMinute(minute);
                break;
            case R.id.spinnerTimer:
                clockTimer.setHour(hour);
                clockTimer.setMinute(minute);
                break;
            default: break;
        }
    }
}

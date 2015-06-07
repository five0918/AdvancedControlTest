package com.excavator.datatimepicker;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import java.util.Calendar;

public class DataTimePickerActivity extends AppCompatActivity {

    private TimePicker mTimePicker;
    private DatePicker mDatePicker;
    private Calendar mCalendar;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_time_picker);
        //获取日历的对象
        mCalendar = Calendar.getInstance();
        //获取年月日时分秒的信息
        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH) + 1;
        day = mCalendar.get(Calendar.DAY_OF_MONTH)+1;
        hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        minute = mCalendar.get(Calendar.MINUTE);
        setTitle(year + "-" + month + "-" + day + "-" + hour + ":" + minute);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);
        mTimePicker = (TimePicker) findViewById(R.id.timePicker);

        //DataPicker动态输入日期
        mDatePicker.init(year, mCalendar.get(Calendar.MONTH),day,new OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year + "-" + (monthOfYear+1)+ "-" + dayOfMonth);
            }
        });

        //TimePicker动态输入时间
        mTimePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay+":"+minute);
            }
        });

       /* //DataPickerDialog动态输入日期
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                setTitle(year + "-" + (monthOfYear+1)+ "-" + dayOfMonth);
            }
        },year,mCalendar.get(Calendar.MONTH),day).show();*/

        //TimePickerDialog动态输入时间
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                setTitle(hourOfDay+":"+minute);
            }
        },hour,minute,true).show();

    }

}

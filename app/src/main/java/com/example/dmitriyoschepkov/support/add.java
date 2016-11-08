package com.example.dmitriyoschepkov.support;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class add extends AppCompatActivity {
    TextView currentDateTime, textView2;
    Calendar dateAndTime=Calendar.getInstance();
    public DBHelper mDatabaseHelper;
    public SQLiteDatabase mSqLiteDatabase;
    RadioButton type1, type2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_update = new Intent(add.this, MainActivity.class);
                finish();
                startActivity(intent_update);
            }
        });
        mDatabaseHelper = new DBHelper(this, "support.db", null, 9);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        currentDateTime=(TextView)findViewById(R.id.currentDateTime);
        setInitialDateTime();
        //type1.isChecked();

    }
    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(add.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }


    // установка начальных даты и времени
    private void setInitialDateTime() {
        String currentDate = DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        currentDateTime.setText(currentDate);
    }
    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };
public void insert (View view){
    String currentDate = DateUtils.formatDateTime(this,
            dateAndTime.getTimeInMillis(),
            DateUtils.FORMAT_NUMERIC_DATE);
    type1 = (RadioButton)findViewById(R.id.type1);
    type2 = (RadioButton)findViewById(R.id.type2);
    String type;
    currentDateTime.setText(currentDate);
    mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
    if (type1.isChecked()){
        type = "'Дневное(09:00 - 09:00)'";
        String insertQuery = "INSERT INTO 'support' (date, actual, type) values " +
                "("+"'"+currentDate+"'"+", 'yes', "+type+");";
        mSqLiteDatabase.execSQL(insertQuery);
    }else if (type2.isChecked()){
        type = "'Ночное(21:00 - 09:00)'";
        String insertQuery = "INSERT INTO 'support' (date, actual, type) values " +
                "("+"'"+currentDate+"'"+", 'yes', "+type+");";
        mSqLiteDatabase.execSQL(insertQuery);
    };

            Toast toast = Toast.makeText(getApplicationContext(),
                    "Дежурство добавлено)",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

}
}

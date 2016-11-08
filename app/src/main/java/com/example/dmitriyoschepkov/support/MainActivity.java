package com.example.dmitriyoschepkov.support;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.format.DateUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public DBHelper mDatabaseHelper;
    public SQLiteDatabase mSqLiteDatabase;
    ListView listViewActual, listViewNoActual;
    Cursor cursorActual, cursorNoActual;
    SimpleCursorAdapter adapterActual, adapterNoActual;
    TextView textActual, textNoActual;
    NotificationManager nm;
    Calendar dateAndTime=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Support");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        mDatabaseHelper = new DBHelper(this, "support.db", null, DBHelper.DATABASE_VERSION);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        listViewActual = (ListView)findViewById(R.id.listViewActual);
        listViewNoActual = (ListView)findViewById(R.id.listViewNoActual);
        textActual = (TextView)findViewById(R.id.textActual);
        textNoActual = (TextView)findViewById(R.id.textNoActual);
        String currentDate = DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE);
        System.out.println("Now: "+currentDate);
        String noActual = "select * from support where date < '"+currentDate+"' order by date;";
        String actual = "select * from support where date >= '"+currentDate+"' order by date;";
        cursorActual = mSqLiteDatabase.rawQuery(actual, null);
        cursorNoActual = mSqLiteDatabase.rawQuery(noActual,null);
        final String[] headersActual = new String[] {DBHelper.DATE_COLUMN, DBHelper.TYPE_COLUMN};
        final String[] headersNoActual = new String[] {DBHelper.DATE_COLUMN, DBHelper.TYPE_COLUMN};
        adapterActual = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursorActual,
                headersActual, new int[]{android.R.id.text1, android.R.id.text2}, 0 );
        adapterNoActual = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursorNoActual,
                headersNoActual, new int[]{android.R.id.text1, android.R.id.text2}, 0 );
        listViewActual.setAdapter(adapterActual);
        listViewNoActual.setAdapter(adapterNoActual);
        String countActual = String.valueOf(adapterActual.getCount());
        String countNoActual = String.valueOf(adapterNoActual.getCount());
        System.out.println("Actual: "+countActual+"; NoActual: "+countNoActual);
        mDatabaseHelper = new DBHelper(getApplicationContext());
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        startService(new Intent(this, MyService.class));
        if (countNoActual == "0"){
            textNoActual.setText("Дежурств, которые прошли - нет :(\n");
        }else textNoActual.setText("Дежурства, которые прошли:");
        if (countActual == "0"){
            textActual.setText("Все дежурства прошли :)");
        }else textActual.setText("Дежурства, которые остались:");


        mSqLiteDatabase.close();

    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void add (View view){
        Intent intent = new Intent(MainActivity.this, add.class);
        startActivity(intent);
    }

}

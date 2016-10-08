package com.example.dmitriyoschepkov.support;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public DBHelper mDatabaseHelper;
    public DBHelper mDBHelper;
    public SQLiteDatabase mSqLiteDatabase;
    TextView current1 , current2 , current3 , current4 , current5, c_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);


        mDatabaseHelper = new DBHelper(this, "support.db", null, 9);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        //String updateActual = "update 'support' set ACTUAL_COLUMN = 'yes';";
        //String delete = "delete from 'support'";
        //mSqLiteDatabase.execSQL(delete);
        current1 = (TextView)findViewById(R.id.current1);
        current2 = (TextView)findViewById(R.id.current2);
        current3 = (TextView)findViewById(R.id.current3);
        current4 = (TextView)findViewById(R.id.current4);
        current5 = (TextView)findViewById(R.id.current5);
        c_month = (TextView)findViewById(R.id.c_month);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        c_month.setText(currentDateTimeString);
        Cursor cursor;


        Date cDate = new Date();
        String fDate = new SimpleDateFormat("dd.MM").format(cDate);
        System.out.println(fDate);


        Double cd = Double.parseDouble(fDate);
        Double cd1 = 10.09;
        //update actual
        cursor = mSqLiteDatabase.query("support", new String[]{DBHelper.DATE_COLUMN, DBHelper.ACTUAL_COLUMN, DBHelper.TYPE_COLUMN},
                null, null, null, null, null);
        int strokD = cursor.getCount();
        if (strokD ==0){
            System.out.print("null");
        }else if (strokD == 1) {
            cursor.moveToPosition(0);
            Double dateD0 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String actual = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(actual);
            if (cd < dateD0) {
                System.out.println("текущая меньше - ОК");
                current1.setTextColor(Color.rgb(0, 153, 76));
            } else if (cd > dateD0) {
                System.out.println("текущая больше");
                //CharSequence styledText = Html.fromHtml("<s>"+ + "</s>");
                current1.setTextColor(Color.LTGRAY);

            }

        }else if (strokD ==2){
            cursor.moveToPosition(0);
            Double dateD0 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD0) {
                System.out.println("текущая меньше - ОК");
                current1.setTextColor(Color.rgb(0, 153, 76));
            } else if (cd > dateD0) {
                System.out.println("текущая больше");
                current1.setTextColor(Color.LTGRAY);
            }
            cursor.moveToPosition(1);
            Double dateD1 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD1){
                System.out.println("текущая меньше - ОК");
                current2.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD1){
                System.out.println("текущая больше");
                current2.setTextColor(Color.LTGRAY);
            };

        }else if (strokD == 3){
            cursor.moveToPosition(0);
            Double dateD0 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD0) {
                System.out.println("текущая меньше - ОК");
                current3.setTextColor(Color.rgb(0, 153, 76));
            } else if (cd > dateD0) {
                System.out.println("текущая больше");
                current3.setTextColor(Color.LTGRAY);
            }
            cursor.moveToPosition(1);
            Double dateD1 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD1){
                System.out.println("текущая меньше - ОК");
                current2.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD1){
                System.out.println("текущая больше");
                current2.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(2);
            Double dateD2 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD2){
                System.out.println("текущая меньше - ОК");
                current3.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD2){
                System.out.println("текущая больше");
                current3.setTextColor(Color.LTGRAY);
            };

        }else if (strokD == 4){
            cursor.moveToPosition(0);
            Double dateD0 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD0) {
                System.out.println("текущая меньше - ОК");
                current1.setTextColor(Color.rgb(0, 153, 76));
            } else if (cd > dateD0) {
                System.out.println("текущая больше");
                current1.setTextColor(Color.LTGRAY);
            }
            cursor.moveToPosition(1);
            Double dateD1 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD1){
                System.out.println("текущая меньше - ОК");
                current2.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD1){
                System.out.println("текущая больше");
                current2.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(2);
            Double dateD2 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD2){
                System.out.println("текущая меньше - ОК");
                current3.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD2){
                System.out.println("текущая больше");
                current3.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(3);
            Double dateD3 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD3){
                System.out.println("текущая меньше - ОК");
                current4.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD3){
                System.out.println("текущая больше");
                current4.setTextColor(Color.LTGRAY);
            };

        }else if (strokD == 5){
            cursor.moveToPosition(0);
            Double dateD0 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD0) {
                System.out.println("текущая меньше - ОК");
                current1.setTextColor(Color.rgb(0, 153, 76));
            } else if (cd > dateD0) {
                System.out.println("текущая больше");
                current1.setTextColor(Color.LTGRAY);
            }
            cursor.moveToPosition(1);
            Double dateD1 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD1){
                System.out.println("текущая меньше - ОК");
                current2.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD1){
                System.out.println("текущая больше");
                current2.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(2);
            Double dateD2 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD2){
                System.out.println("текущая меньше - ОК");
                current3.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD2){
                System.out.println("текущая больше");
                current3.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(3);
            Double dateD3 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD3){
                System.out.println("текущая меньше - ОК");
                current4.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD3){
                System.out.println("текущая больше");
                current4.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(4);
            Double dateD4 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD4){
                System.out.println("текущая меньше - ОК");
                current5.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD4){
                System.out.println("текущая больше");
                current5.setTextColor(Color.LTGRAY);
            };
        }else if (strokD > 5){
            cursor.moveToPosition(0);
            Double dateD0 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD0) {
                System.out.println("текущая меньше - ОК");
                current1.setTextColor(Color.rgb(0, 153, 76));
            } else if (cd > dateD0) {
                System.out.println("текущая больше");
                current1.setTextColor(Color.LTGRAY);
            }
            cursor.moveToPosition(1);
            Double dateD1 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD1){
                System.out.println("текущая меньше - ОК");
                current2.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD1){
                System.out.println("текущая больше");
                current2.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(2);
            Double dateD2 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD2){
                System.out.println("текущая меньше - ОК");
                current3.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD2){
                System.out.println("текущая больше");
                current3.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(3);
            Double dateD3 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD3){
                System.out.println("текущая меньше - ОК");
                current4.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD3){
                System.out.println("текущая больше");
                current4.setTextColor(Color.LTGRAY);
            };
            cursor.moveToPosition(4);
            Double dateD4 = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            if (cd < dateD4){
                System.out.println("текущая меньше - ОК");
                current5.setTextColor(Color.rgb(0, 153, 76));
            }else if (cd > dateD4){
                System.out.println("текущая больше");
                current5.setTextColor(Color.LTGRAY);
            };
        }

        cursor.close();
        //end update
        cursor = mSqLiteDatabase.query("support", new String[]{DBHelper.DATE_COLUMN, DBHelper.ACTUAL_COLUMN, DBHelper.TYPE_COLUMN},
                null, null, null, null, null);
        int strok = cursor.getCount();
        System.out.println(strok);
        if (strok == 0){
            current1.setText("-");
            current2.setText("-");
            current3.setText("-");
            current4.setText("-");
            current5.setText("-");
        } else if (strok == 1) {
            cursor.moveToPosition(0);
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            Double dateD = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date+" "+type+" "+actual);
            current1.setText(""+date+" "+type);





        }    else if (strok == 2) {
            cursor.moveToPosition(0);
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));

            System.out.println(strok);
            System.out.println(date+" "+type+" "+actual);
            current1.setText(""+date+" "+type);
            cursor.moveToPosition(1);
            String date1 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type1 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual1 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));

            System.out.println(strok);
            System.out.println(date1+" "+type1+" "+actual1);
            current2.setText(""+date1+" "+type1);
        }else if (strok == 3) {
            cursor.moveToPosition(0);
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date+" "+type+" "+actual);
            current1.setText(""+date+" "+type);
            cursor.moveToPosition(1);
            String date1 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type1 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual1 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date1+" "+type1+" "+actual1);
            current2.setText(""+date1+" "+type1);
            cursor.moveToPosition(2);
            String date2 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type2 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual2 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date2+" "+type2+" "+actual2);
            current3.setText(""+date2+" "+type2);
        }else if (strok == 4) {
            cursor.moveToPosition(0);
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date+" "+type+" "+actual);
            current1.setText(""+date+" "+type);
            cursor.moveToPosition(1);
            String date1 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type1 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual1 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date1+" "+type1+" "+actual1);
            current2.setText(""+date1+" "+type1);
            cursor.moveToPosition(2);
            String date2 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type2 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual2 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date2+" "+type2+" "+actual2);
            current3.setText(""+date2+" "+type2);
            cursor.moveToPosition(3);
            String date3 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type3 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual3 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date3+" "+type3+" "+actual3);
            current4.setText(""+date3+" "+type3);
        }else if (strok == 5) {
            cursor.moveToPosition(0);
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date+" "+type+" "+actual);
            current1.setText(""+date+" "+type);
            cursor.moveToPosition(1);
            String date1 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type1 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual1 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date1+" "+type1+" "+actual1);
            current2.setText(""+date1+" "+type1);
            cursor.moveToPosition(2);
            String date2 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type2 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual2 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date2+" "+type2+" "+actual2);
            current3.setText(""+date2+" "+type2);
            cursor.moveToPosition(3);
            String date3 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type3 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual3 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date3+" "+type3+" "+actual3);
            current4.setText(""+date3+" "+type3);
            cursor.moveToPosition(4);
            String date4 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type4 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual4 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date4+" "+type4+" "+actual4);
            current5.setText(""+date4+" "+type4);
        }else if (strok > 5) {
            cursor.moveToPosition(0);
            String date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            Double dateD = cursor.getDouble(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date+" "+type+" "+actual);
            current1.setText(""+date+" "+type);
            cursor.moveToPosition(1);
            String date1 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type1 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual1 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date1+" "+type1+" "+actual1);
            current2.setText(""+date1+" "+type1);
            cursor.moveToPosition(2);
            String date2 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type2 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual2 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date2+" "+type2+" "+actual2);
            current3.setText(""+date2+" "+type2);
            cursor.moveToPosition(3);
            String date3 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type3 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual3 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date3+" "+type3+" "+actual3);
            current4.setText(""+date3+" "+type3);
            cursor.moveToPosition(4);
            String date4 = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String type4 = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            String actual4 = cursor.getString(cursor.getColumnIndex(DBHelper.ACTUAL_COLUMN));
            System.out.println(strok);
            System.out.println(date4+" "+type4+" "+actual4);
            current5.setText(""+date4+" "+type4);

        }

        cursor.close();
    }

    public void add (View view){
        Intent intent = new Intent(MainActivity.this, add.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.update) {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }else if (id == R.id.drop) {
            mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
            String delete = "delete from 'support'";
            mSqLiteDatabase.execSQL(delete);
        }

        return super.onOptionsItemSelected(item);
    }
}

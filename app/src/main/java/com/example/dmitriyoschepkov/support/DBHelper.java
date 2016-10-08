package com.example.dmitriyoschepkov.support;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
/**
 * Created by Dmitriy.Oschepkov on 22.09.2016.
 */
public class DBHelper extends SQLiteOpenHelper implements BaseColumns {


    //name BD
    private static final String DATABASE_NAME = "support.db";
    //version
    private static final int DATABSE_VERSION = 9;


    //name  table
    public static final String DATABASE_TABLE = "support";
    //name column
    public static final String DATE_COLUMN = "date";
    public static final String ACTUAL_COLUMN = "actual";
    public static final String TYPE_COLUMN = "type";


    //create
    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE
            + " (" +BaseColumns._ID + " integer primary key autoincrement, "
            + DATE_COLUMN + " CHAR, "
            + ACTUAL_COLUMN + " CHAR, "
            + TYPE_COLUMN + " CHAR);";


    DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABSE_VERSION);

    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler){
        super(context, name, factory, version, errorHandler);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_SCRIPT);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

        // Создаём новую таблицу
        onCreate(db);
    }
    @Override
    public void onDowngrade (SQLiteDatabase db, int oldVersion, int newVersion){

    }
}



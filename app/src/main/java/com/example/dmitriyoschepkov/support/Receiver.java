package com.example.dmitriyoschepkov.support;

/**
 * Created by Dmitriy.Oschepkov on 08.11.2016.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.text.format.DateUtils;
import android.util.Log;
import java.util.Calendar;
public class Receiver extends BroadcastReceiver {
    private static final int NOTIFY_ID = 1;
    final String LOG = "myLogs";
    Calendar dateAndTime=Calendar.getInstance();
    public DBHelper mDatabaseHelper;
    public SQLiteDatabase mSqLiteDatabase;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        String currentDate = DateUtils.formatDateTime(context,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE);
        System.out.println("Now: "+currentDate);
        String selectToday = "Select * FROM support where date ='"+currentDate+"';";
        mDatabaseHelper = new DBHelper(context, "support.db", null, DBHelper.DATABASE_VERSION);
        mSqLiteDatabase = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = mSqLiteDatabase.rawQuery(selectToday, null);
        int count = cursor.getCount();
        System.out.println("Строк по дате: "+ count);
        if (count==0){
            System.out.print("сегодня событий нет");
        }else if(count>0){
            cursor.moveToFirst();
            String select_date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COLUMN));
            String select_type = cursor.getString(cursor.getColumnIndex(DBHelper.TYPE_COLUMN));
            System.out.println(select_date+"; "+select_type);
            Log.d(LOG, "выполняется: " + intent.getAction());
            Intent notificationIntent = new Intent(context, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(context,
                    0, notificationIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);
            Resources res = context.getResources();
            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentIntent(contentIntent)
                    .setSmallIcon(R.drawable.ic_bug_report_white_18dp)
                    .setPriority(Notification.PRIORITY_HIGH)// большая картинка
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.ic_bug_report_red_900_48dp))
                    .setTicker("Дежурство") // текст в строке состояния
                    .setWhen(System.currentTimeMillis())
                    .setAutoCancel(true)
                    .setStyle(new Notification.InboxStyle()
                            .addLine(select_date+" - "+select_type)
                            .setBigContentTitle("Сегодня дежурство!"))
                    //.setSummaryText("хз"))
                    .setContentText(select_date+" - "+select_type)
                    .setContentTitle("Сегодня дежурство!");
            Notification notification = builder.build();
            notification.defaults = Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE;
            NotificationManager notificationManager = (NotificationManager) context
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFY_ID, notification);
        }

        cursor.close();
        mDatabaseHelper.close();
    }



}

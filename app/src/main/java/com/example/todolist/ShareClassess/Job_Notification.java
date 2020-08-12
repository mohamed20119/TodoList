package com.example.todolist.ShareClassess;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;

import androidx.annotation.RequiresApi;

import com.example.todolist.R;

public class Job_Notification {
    private Context context ;
    private  PendingIntent JobTitleIntent;
    private  NotificationManager notificationManager;
    private  String message;

    public Job_Notification(Context context,String message, NotificationManager notificationManager) {
        this.context = context;
      this.message = message;
        this.notificationManager = notificationManager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotification()
    {
        NotificationChannel notificationChannel = new NotificationChannel("920","JobNotification",NotificationManager.IMPORTANCE_HIGH);
        Notification.Builder notification = new Notification.Builder(this.context,"920");
        notification.setContentTitle("Todo List Notification");
        notification.setContentText(this.message.toString());
        notification.setVisibility(Notification.VISIBILITY_PUBLIC);
        notification.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.todolisticon);
        notification.setContentIntent(JobTitleIntent);
        this.notificationManager.createNotificationChannel(notificationChannel);
        this.notificationManager.notify(10,notification.build());

    }

    public void createNotificationForLowAndroidSystem()
    {
       // NotificationChannel notificationChannel = new NotificationChannel("920","JobNotification",NotificationManager.IMPORTANCE_HIGH);
        Notification.Builder notification = new Notification.Builder(this.context);
        notification.setContentTitle("Todo List Notification");
        notification.setContentText(this.message.toString());
        notification.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            notification.setVisibility(Notification.VISIBILITY_PUBLIC);
        }
        notification.setAutoCancel(true);
        notification.setSmallIcon(R.drawable.todolisticon);
        notification.setContentIntent(JobTitleIntent);
        this.notificationManager.notify(10,notification.build());

    }
}

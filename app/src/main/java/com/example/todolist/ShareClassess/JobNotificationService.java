package com.example.todolist.ShareClassess;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.todolist.AddJob.AddJobUI;
import com.example.todolist.ShareClassess.JobDBModel;
import com.example.todolist.ShareClassess.Job_Notification;

public class JobNotificationService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("create service","yes");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service runnin","running");
        String message = intent.getStringExtra("message");

        Intent i = new Intent(this, AddJobUI.class);
        i.putExtra("title",message);
        i.putExtra("hour",intent.getIntExtra("hour",0));
        i.putExtra("min",intent.getIntExtra("min",0));
        i.putExtra("day",intent.getIntExtra("day",0));
        i.putExtra("month",intent.getIntExtra("month",0));
        i.putExtra("year",intent.getIntExtra("year",0));
        i.putExtra("who","iam notification");

      //  PendingIntent pendingIntent =PendingIntent.getActivities(this,1000,new Intent[]{i},PendingIntent.FLAG_UPDATE_CURRENT); // put value when create special job activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            new Job_Notification(getBaseContext(),message,(NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotification();
            onDestroy();
        }else
        {
            new Job_Notification(getBaseContext(),message,(NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationForLowAndroidSystem();

            onDestroy();
        }
        Log.e("message form service : ",message);
        return START_REDELIVER_INTENT;

    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}

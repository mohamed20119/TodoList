package com.example.todolist.AddJob;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.todolist.JobList.JobAdaptor;
import com.example.todolist.ShareClassess.JobDBModel;
import com.example.todolist.ShareClassess.JobNotificationService;

import java.util.Calendar;

public class AddJobPresenter {
      AddJobModel addJobModel ;
    private AlarmManager alarmManager ;
    private Context context ;
    private JobDBModel dbModel;
    private  Intent intent;

    public AddJobPresenter(AddJobModel addJobModel, AlarmManager alarmManager, Context context, JobDBModel dbModel) {
        this.addJobModel = addJobModel;
        this.alarmManager = alarmManager;
        this.context = context;
        this.dbModel = dbModel;
    }


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onTimeChanged(String currentcase ,int hourOfDay, int minute,int day, int month,int year) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        if(minute<=12)
        {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        }else
        {
            calendar.set(Calendar.HOUR_OF_DAY, (hourOfDay)-12);
        }
        this.intent = new Intent(this.context, JobNotificationService.class);
        this.intent.putExtra("message",this.dbModel.getTitle());
        this.intent.putExtra("hour",this.dbModel.getHour());
        this.intent.putExtra("min",this.dbModel.getMin());
        this.intent.putExtra("day",this.dbModel.getDay());
        this.intent.putExtra("month",this.dbModel.getMonth());
        this.intent.putExtra("year",this.dbModel.getYear());

        PendingIntent AlarmPendingIntent = PendingIntent.getService(this.context,10,this.intent,PendingIntent.FLAG_UPDATE_CURRENT);

      if(currentcase.equals("delete"))
      {
          this.alarmManager.cancel(AlarmPendingIntent);
          AlarmPendingIntent.cancel();
      }else
      {
          this.alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmPendingIntent);
      }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void SetJob(String title , int hour,int min , int year,int month,int day)
    {
        addJobModel.insert(title,hour,min,day,month,year);
        onTimeChanged("insert",hour,min,day,month,year);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void DeleteJob(int id ,String title ,int hour,int min , int year,int month,int day)
    {
        addJobModel.delete(id);
        onTimeChanged("delete",hour,min,day,month,year);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void UpdateJob(int id , String title , int hour,int min , int year,int month,int day)
    {
       onTimeChanged("delete", addJobModel.SelectOne(id).getHour(), addJobModel.SelectOne(id).getMin(), addJobModel.SelectOne(id).getDay(), addJobModel.SelectOne(id).getMonth(), addJobModel.SelectOne(id).getYear());
        addJobModel.update(id,title,hour,min,day,month,year);
        onTimeChanged("update",hour,min,day,month,year);
    }
}
package com.example.todolist.ShareClassess;

import io.realm.RealmObject;

public class JobDBModel extends RealmObject {
    private  String title ;
    private int hour, min,day,month,year;
    public  int id =0;
    public static int counterforid =0;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }




    public JobDBModel() {
        counterforid=counterforid+1;
        id = counterforid+id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}

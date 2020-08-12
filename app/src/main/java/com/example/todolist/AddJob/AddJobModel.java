package com.example.todolist.AddJob;

import com.example.todolist.ShareClassess.DB_statment;
import com.example.todolist.ShareClassess.JobDBModel;

public class AddJobModel {
     DB_statment db_statment ;

    public AddJobModel(DB_statment db_statment) {
        this.db_statment = db_statment;
    }

    public void insert(String title , int hour,int min,int day,int month , int year)
    {
        this.db_statment.insert(title,hour,min,day,month,year);
    }

    public void delete(int id)
    {
        this.db_statment.delete(id);
    }

    public void update(int id ,String title ,int hour , int min , int day , int month , int year)
    {
        this.db_statment.update(id, title, hour, min, day, month, year);
    }
    public JobDBModel SelectOne(int id )
    {
        return  this.db_statment.select(id);
    }
}

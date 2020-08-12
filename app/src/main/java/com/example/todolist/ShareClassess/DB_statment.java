package com.example.todolist.ShareClassess;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class DB_statment {
    private JobDBModel jobDBModel ;
    private  Realm realmconnection;

    public DB_statment(JobDBModel jobDBModel, Realm realm) {
        this.jobDBModel = jobDBModel;
        this.realmconnection = realm;
    }

    public void insert(String title ,int hour , int min , int day , int month , int year)
    {
        this.realmconnection.beginTransaction();
        this.jobDBModel.setTitle(title);
        this.jobDBModel.setHour(hour);
        this.jobDBModel.setMin(min);
        this.jobDBModel.setDay(day);
        this.jobDBModel.setMonth(month);
        this.jobDBModel.setYear(year);
        this.realmconnection.copyToRealm(this.jobDBModel);
        this.realmconnection.commitTransaction();
    }

    public void update (int id ,String title ,int hour , int min , int day , int month , int year)
    {
        this.realmconnection.beginTransaction();
        RealmResults<JobDBModel> result = this.realmconnection.where(JobDBModel.class).equalTo("id",id).findAll();
        for(JobDBModel res : result)
        {
            res.deleteFromRealm();
            this.jobDBModel.setTitle(title);
            this.jobDBModel.setHour(hour);
            this.jobDBModel.setMin(min);
            this.jobDBModel.setDay(day);
            this.jobDBModel.setMonth(month);
            this.jobDBModel.setYear(year);
            this.realmconnection.copyToRealm(this.jobDBModel);
        }


        this.realmconnection.commitTransaction();
    }

    public void delete(int id)
    {
        realmconnection.beginTransaction();
        realmconnection.where(JobDBModel.class).equalTo("id",id).findAll().deleteAllFromRealm();
        realmconnection.commitTransaction();
    }

    public ArrayList<JobDBModel> selectAll()
    {
        ArrayList<JobDBModel> list = new ArrayList<>();
        this.realmconnection.beginTransaction();
        RealmResults<JobDBModel> result = this.realmconnection.where(JobDBModel.class).findAll();
        for(JobDBModel res : result)
        {
            list.add(res);
        }
        this.realmconnection.commitTransaction();
        return list;
    }

    public JobDBModel select(int id)
    {
        JobDBModel jobDBModel = null;
        ArrayList<JobDBModel> list = new ArrayList<>();
        this.realmconnection.beginTransaction();
        RealmResults<JobDBModel> result = this.realmconnection.where(JobDBModel.class).equalTo("id",id).findAll();
        for(JobDBModel res : result)
        {
            jobDBModel = res ;
        }
        this.realmconnection.commitTransaction();
        return jobDBModel;
    }
}

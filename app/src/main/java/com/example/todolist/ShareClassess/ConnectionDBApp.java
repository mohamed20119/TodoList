package com.example.todolist.ShareClassess;

import android.app.Application;

import com.example.todolist.JobList.JobAdaptor;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ConnectionDBApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Realm getinit()
    {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);
         Realm realm = Realm.getDefaultInstance();
        return realm;
    }




}

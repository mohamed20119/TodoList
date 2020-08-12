package com.example.todolist.JobList;

import com.example.todolist.ShareClassess.DB_statment;
import com.example.todolist.ShareClassess.JobDBModel;

import java.util.ArrayList;

import io.realm.RealmResults;

public class JObListModel {
    private DB_statment db_statment ;

    public JObListModel(DB_statment db_statment) {
        this.db_statment = db_statment;
    }

    public ArrayList<JobDBModel> GetALlJob ()
    {
        return db_statment.selectAll();
    }

    public JobDBModel GetSPecialJob(int id)
    {
        return db_statment.select(id);
    }

}

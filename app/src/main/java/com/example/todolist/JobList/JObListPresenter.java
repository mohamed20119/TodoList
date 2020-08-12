package com.example.todolist.JobList;

import com.example.todolist.ShareClassess.JobDBModel;

import java.util.ArrayList;

public class JObListPresenter {

     private JObListModel jObListModel ;

    public JObListPresenter(JObListModel jObListModel) {
        this.jObListModel = jObListModel;
    }

    public ArrayList<JobDBModel> GetALlJob (){return this.jObListModel.GetALlJob();}
    public JobDBModel GetSPecialJob(int id){return this.jObListModel.GetSPecialJob(id);}
}

package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;

import com.example.todolist.AddJob.AddJobUI;
import com.example.todolist.JobList.JObListModel;
import com.example.todolist.JobList.JObListPresenter;
import com.example.todolist.JobList.JobAdaptor;
import com.example.todolist.ShareClassess.ConnectionDBApp;
import com.example.todolist.ShareClassess.DB_statment;
import com.example.todolist.ShareClassess.JobDBModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
   private androidx.recyclerview.widget.RecyclerView joblist;
   private JObListPresenter jObListPresenter;
   private  Realm realm;
   private JobAdaptor jobAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.realm = ((ConnectionDBApp)getApplication()).getinit();
        this.jObListPresenter = new JObListPresenter(new JObListModel(new DB_statment(new JobDBModel() , this.realm)));

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddJobUI.class);
                intent.putExtra("who","iam insert");
                startActivity(intent);
            }
        });
    }

    public void init_jobList()
    {
        this.joblist = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.joblist);
        this.jobAdaptor = new JobAdaptor(R.layout.jobrecyclelist,this,this.jObListPresenter.GetALlJob(),this);
        this.jobAdaptor.notifyDataSetChanged();
        this.joblist.setAdapter(jobAdaptor);
        this.joblist.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        init_jobList();

    }
}

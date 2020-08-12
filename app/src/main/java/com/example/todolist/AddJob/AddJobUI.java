package com.example.todolist.AddJob;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.ShareClassess.ConnectionDBApp;
import com.example.todolist.ShareClassess.DB_statment;
import com.example.todolist.ShareClassess.JobDBModel;

import io.realm.Realm;

public class AddJobUI extends AppCompatActivity {
    private TextView datetext;
    private TextView timetext;
    private EditText jobtitle;
    private JobDBModel jobDBModel;
    private AddJobPresenter addJobPresenter;
    private int hour,min,day,month,year;
    private Button addbtn ;
    private Button updatebtn ;
    private Button deletebtn ;
    private int id ;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_ui);
       this.i= getIntent();
        this.jobDBModel = new JobDBModel();
        initcomponent();
    }

    public void initcomponent()
    {
        this.datetext = (TextView)findViewById(R.id.date);
        this.timetext = (TextView)findViewById(R.id.time);
        this.jobtitle = (EditText)findViewById(R.id.jobtitle);
        this.addbtn = (Button)findViewById(R.id.addbtn);
        this.updatebtn = (Button)findViewById(R.id.updbtn);
        this.deletebtn = (Button)findViewById(R.id.deletebtn);
        this.updatebtn.setVisibility(View.GONE);
        this.deletebtn.setVisibility(View.GONE);

       if(this.i.getStringExtra("who").equals("iam update"))
       {
           this.addbtn.setVisibility(View.GONE);
           this.updatebtn.setVisibility(View.VISIBLE);
           this.deletebtn.setVisibility(View.VISIBLE);
           /*will insert data into job DB Model*/
           EnterDataToJobDBModel();
           PassDataToWedgit();

           this.jobtitle.setText(i.getStringExtra("title"));
           String time = String.valueOf(this.jobDBModel.getHour())+" : "+String.valueOf(this.jobDBModel.getMin());
           timetext.setText(time);
           String date =String.valueOf(this.jobDBModel.getDay())+" : "+String.valueOf(this.jobDBModel.getMonth()+1)+" : "+String.valueOf(this.jobDBModel.getYear());
           datetext.setText(date);
           this.id = i.getIntExtra("id",0);
       }else if(this.i.getStringExtra("who").equals("iam notification"))
       {
           this.addbtn.setVisibility(View.GONE);
           this.updatebtn.setVisibility(View.GONE);
           this.deletebtn.setVisibility(View.GONE);
       }
        Realm realm= ((ConnectionDBApp)getApplication()).getinit();
        this.addJobPresenter = new AddJobPresenter(new AddJobModel(new DB_statment(jobDBModel,realm)),(AlarmManager)getSystemService(ALARM_SERVICE),getApplicationContext(),jobDBModel);
    }

public void EnterDataToJobDBModel()
{
    this.jobDBModel.setTitle(i.getStringExtra("title"));
    this.jobDBModel.setHour(i.getIntExtra("hour",0));
    this.jobDBModel.setMin(i.getIntExtra("min",0));
    this.jobDBModel.setDay(i.getIntExtra("day",0));
    this.jobDBModel.setMonth(i.getIntExtra("month",0));
    this.jobDBModel.setYear(i.getIntExtra("year",0));
}

public void PassDataToWedgit()
{
    this.hour = this.jobDBModel.getHour();
    this.month = this.jobDBModel.getMonth();
    this.day = this.jobDBModel.getDay();
    this.min = this.jobDBModel.getMin();
    this.year = this.jobDBModel.getYear();
}
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void AddDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                 day = datePicker.getDayOfMonth();
                 month = datePicker.getMonth();
                 year = datePicker.getYear();

                String date = String.valueOf(day)+" : "+String.valueOf(month+1)+" : "+String.valueOf(year);
                datetext.setText(date);
            }
        });
        datePickerDialog.show();
    }


    public void AddTime(View view) {
      TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
          @RequiresApi(api = Build.VERSION_CODES.M)
          @Override
          public void onTimeSet(TimePicker timePicker, int i, int i1) {
             hour = timePicker.getHour();
              min = timePicker.getMinute();
              String time = String.valueOf(hour)+" : "+String.valueOf(min);
              timetext.setText(time);
          }
      },1,1,false);
      timePickerDialog.show();

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void addjob(View view) {


        // addJobPresenter.SetJob(this.jobtitle.getText().toString(),this.timetext.getText().toString(),this.datetext.getText().toString());
        addJobPresenter.SetJob(this.jobtitle.getText().toString(),hour,min,year, this.month,day);
        Log.e("month",String.valueOf(month));
        finish();

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void UpdateJob(View view) {

        this.addJobPresenter.UpdateJob(this.id,this.jobtitle.getText().toString(),this.hour,this.min,this.year,this.month,this.day);
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void DeleteJob(View view) {

        this.addJobPresenter.DeleteJob(this.id,jobtitle.getText().toString(),this.hour,this.min,this.year,this.month,this.day);

        finish();
    }
}

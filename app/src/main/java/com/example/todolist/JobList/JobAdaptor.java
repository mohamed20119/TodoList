package com.example.todolist.JobList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.AddJob.AddJobUI;
import com.example.todolist.MainActivity;
import com.example.todolist.R;
import com.example.todolist.ShareClassess.JobDBModel;

import java.util.ArrayList;

public class JobAdaptor extends RecyclerView.Adapter<JobAdaptor.JobHolder> {
   private int res ;
    private Context context ;
    private ArrayList<JobDBModel> list ;
    private Activity activity;
    public JobAdaptor(int res, Context context, ArrayList<JobDBModel> list,Activity activity) {
        this.res = res;
        this.context = context;
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public JobHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(res,null);
        return new JobHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final JobHolder holder, final int position) {
        holder.jobtitle.setText(this.list.get(position).getTitle().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddJobUI.class);
                intent.putExtra("title",list.get(position).getTitle().toString());
                intent.putExtra("id",list.get(position).id);
                intent.putExtra("hour",list.get(position).getHour());
                intent.putExtra("min",list.get(position).getMin());
                intent.putExtra("day",list.get(position).getDay());
                intent.putExtra("month",list.get(position).getMonth());
                intent.putExtra("year",list.get(position).getYear());
                intent.putExtra("who","iam update");
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class JobHolder extends RecyclerView.ViewHolder {
        TextView jobtitle;
        androidx.cardview.widget.CardView cardView ;
        public JobHolder(@NonNull View itemView) {
            super(itemView);
            this.jobtitle = (TextView)itemView.findViewById(R.id.jobtitle);
            this.cardView = (androidx.cardview.widget.CardView)itemView.findViewById(R.id.jobcardview);
        }
    }


}

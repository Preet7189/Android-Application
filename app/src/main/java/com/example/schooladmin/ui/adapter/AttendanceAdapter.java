package com.example.schooladmin.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooladmin.R;
import com.example.schooladmin.ui.activity.AttendanceListActivity;
import com.example.schooladmin.ui.model.Attendance;

import java.util.ArrayList;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder>{
    private ArrayList<Attendance> listdata;
    AttendanceListActivity attendanceListActivity;

    //Get List data from activity
    public AttendanceAdapter(ArrayList<Attendance> listdata, AttendanceListActivity attendanceListActivity) {
        this.listdata = listdata;
        this.attendanceListActivity = attendanceListActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //set the layout of attendance list item from xml
        View listItem= layoutInflater.inflate(R.layout.attendance_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Attendance myListData = listdata.get(position);
        //set date basic pay and click on main row
        holder.txtDate.setText(myListData.getDate());

        holder.lilMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to AttendanceListActivity on click of row item
                Intent yourIntent = new Intent(attendanceListActivity, AttendanceListActivity.class);
                attendanceListActivity.finish();
                attendanceListActivity.startActivity(yourIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtDate;
        public LinearLayout lilMain;
        public ViewHolder(View itemView) {
            super(itemView);
            //Get the id of the row items
            this.txtDate = (TextView) itemView.findViewById(R.id.txtDate);
            lilMain = (LinearLayout)itemView.findViewById(R.id.lilMain);
        }
    }
}  
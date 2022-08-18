package com.example.schooladmin.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.schooladmin.R;
import com.example.schooladmin.ui.activity.StudentDetailsActivity;
import com.example.schooladmin.ui.activity.StudentListActivity;
import com.example.schooladmin.ui.activity.MarkAttendanceActivity;
import com.example.schooladmin.ui.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    private ArrayList<Student> listdata;
    StudentListActivity employeeListActivity;
    String TAG;
    //Get the list of student and tag from the activity
    public StudentAdapter(ArrayList<Student> listdata, StudentListActivity employeeListActivity, String TAG) {
        this.listdata = listdata;
        this.employeeListActivity = employeeListActivity;
        this.TAG = TAG;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //set the layout of the student list row item
        View listItem= layoutInflater.inflate(R.layout.student_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Student myListData = listdata.get(position);
        //set the data from the list object
        holder.txtName.setText(myListData.getFullName());
        holder.txtgrade.setText(myListData.getGrade());
        holder.txtstudentid.setText(myListData.getStudentid());
        holder.lilMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //As per the tag from the previous screen open different activity with passing student object

                if(TAG.equalsIgnoreCase("EmployeeList"))
                {
                    //Redirect to the student details screen and pass the student object using intent bundle
                    Intent yourIntent = new Intent(employeeListActivity, StudentDetailsActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("employee",myListData );
                    yourIntent.putExtras(b); //pass bundle to your intent
                    employeeListActivity.finish();
                    employeeListActivity.startActivity(yourIntent);
                }
                else if(TAG.equalsIgnoreCase("MarkAttendance"))
                {
                    //Redirect to the mark attendance screen and pass the student object using intent bundle
                    Intent yourIntent = new Intent(employeeListActivity, MarkAttendanceActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("employee",myListData );
                    yourIntent.putExtras(b); //pass bundle to your intent
                    employeeListActivity.finish();
                    employeeListActivity.startActivity(yourIntent);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName,txtgrade,txtstudentid;
        public LinearLayout lilMain;
        public ViewHolder(View itemView) {
            super(itemView);
            //Get the id of the all the fields
            this.txtName = (TextView) itemView.findViewById(R.id.txtName);
            this.txtgrade = (TextView) itemView.findViewById(R.id.txtGrade);
            this.txtstudentid = (TextView) itemView.findViewById(R.id.txtStudentid);
            lilMain = (LinearLayout)itemView.findViewById(R.id.lilMain);
        }
    }
}  
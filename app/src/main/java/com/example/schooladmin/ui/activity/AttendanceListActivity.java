package com.example.schooladmin.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooladmin.R;
import com.example.schooladmin.ui.adapter.AttendanceAdapter;
import com.example.schooladmin.ui.model.Attendance;
import com.example.schooladmin.ui.model.Student;

import java.util.ArrayList;
import java.util.Calendar;

public class AttendanceListActivity extends AppCompatActivity {
    TextView txtName,txtGrade,txtStudentid,txtStartDate,txtEndDate;
    Student student;
    Button btnGenerate;
    ImageView ic_back;

    //Create attendance arraylist
    ArrayList<Attendance> attendanceArrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendance_list_item);

        //Get the student object from the previous screen
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        student = (Student) bundle.getSerializable("employee");

        //Get Ids of all the required fields

        txtName = (TextView)findViewById(R.id.txtName);
        txtGrade = (TextView)findViewById(R.id.txtGrade);
        txtStudentid = (TextView)findViewById(R.id.txtStudentid);


        ic_back = (ImageView) findViewById(R.id.ic_back);

        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Open date picker and set picked date
        txtStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(txtStartDate);
            }
        });
        //Open date picker and set picked date
        txtEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(txtEndDate);
            }
        });



        //Set all the value of student to related views
        txtName.setText(student.getFullName());
        txtGrade.setText(student.getGrade());
        txtStudentid.setText(student.getStudentid());




        //Attaching recycler view to custom adapter and passing arraylist
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        AttendanceAdapter adapter = new AttendanceAdapter(attendanceArrayList,this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    //Method for opening date picker dialog and set value to the textview in argument
    private void openDatePicker(final TextView txtView) {
        final Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(AttendanceListActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtView.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}

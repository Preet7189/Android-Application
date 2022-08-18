package com.example.schooladmin.ui.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooladmin.MainActivity;
import com.example.schooladmin.R;
import com.example.schooladmin.sqlite.DatabaseHandler;
import com.example.schooladmin.ui.adapter.StudentAdapter;
import com.example.schooladmin.ui.model.Student;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StudentListActivity extends AppCompatActivity {
    ImageView ic_back;

    LinearLayout dateView;
    TextView txtDay,txtDate;
    String Tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        //Get tag as per the selection from the HomeFragment
        Tag = bundle.getString("TAG");

        //Get Ids of all the required fields
        ic_back = (ImageView) findViewById(R.id.ic_back);
        txtDay = (TextView) findViewById(R.id.txtDay);
        txtDate = (TextView) findViewById(R.id.txtDate);
        dateView = (LinearLayout) findViewById(R.id.dateView);

        //Get current date and day
        setCurrentDateandDay();

        //Create data base object for doing database related operations
        DatabaseHandler db = new DatabaseHandler(this);


        ArrayList<Student> myListData = db.getAllEmployee();

        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentListActivity.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });

        //Open date picker for day
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
        //Open date picker for date
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });

        //check as per the tag and visible and hide the date view
        if(Tag.equalsIgnoreCase("MarkAttendance"))
        {
            dateView.setVisibility(View.VISIBLE);
        }
        else
        {
            dateView.setVisibility(View.GONE);
        }


        for (Student cn : myListData) {
            String log = "Id: " + cn.get_id() + " ,Name: " + cn.getFullName() + " ,Phone: " +
                    cn.getMobileNo();
            // Writing Contacts to log
            Log.e("Name: ", log);
        }

        //Attaching recycler view to custom adapter and passing arraylist
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StudentAdapter adapter = new StudentAdapter(myListData,this,Tag);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setCurrentDateandDay() {

        //Set current date
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());

        //Day format
        SimpleDateFormat simpledateformatDay = new SimpleDateFormat("EEEE");
        String dayOfWeek = simpledateformatDay.format(date);
        txtDay.setText(dayOfWeek);

        //Date format
        SimpleDateFormat simpledateformatDate = new SimpleDateFormat("dd/MM/yyyy");
        String dateValue = simpledateformatDate.format(date);
        txtDate.setText(dateValue);

    }

    private void openDatePicker() {
        //Open date picker
        final Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(StudentListActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Calendar calendar = Calendar.getInstance();
                        calendar.clear(); // Sets hours/minutes/seconds/milliseconds to zero
                        calendar.set(year , (monthOfYear), dayOfMonth);
                        Date result = calendar.getTime();

                        String dayOfWeek = simpledateformat.format(result);
                        txtDay.setText(dayOfWeek);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


}

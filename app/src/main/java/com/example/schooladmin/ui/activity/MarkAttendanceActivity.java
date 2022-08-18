package com.example.schooladmin.ui.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schooladmin.R;
import com.example.schooladmin.ui.model.Student;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MarkAttendanceActivity extends AppCompatActivity {
    Student student;
    TextView txtName,txtgrade,txtstudentid;
    TextView txtDay,txtDate;
    ImageView ic_back;

    RadioButton radioPresent,radioAbsent;
    Button btnsubmit,btnUpdate,btnDelete;
    LinearLayout lilSubmit,lilUpdateDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        student = (Student) bundle.getSerializable("employee");

        //Get Ids of all the required fields
        txtName = (TextView)findViewById(R.id.txtName);
        txtgrade = (TextView)findViewById(R.id.txtGrade);
        txtstudentid = (TextView)findViewById(R.id.txtStudentid);
        txtDay = (TextView) findViewById(R.id.txtDay);
        txtDate = (TextView) findViewById(R.id.txtDate);
        radioPresent= (RadioButton) findViewById(R.id.radioPresent);
        radioAbsent= (RadioButton) findViewById(R.id.radioAbsent);
        ic_back= (ImageView) findViewById(R.id.ic_back);


        btnsubmit = (Button)findViewById(R.id.btnsubmit);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        lilSubmit = (LinearLayout)findViewById(R.id.lilSubmit);
        lilUpdateDelete = (LinearLayout)findViewById(R.id.lilUpdateDelete);

//Set all the value of student to related views
        txtName.setText(student.getFullName());
        txtgrade.setText(student.getGrade());
        txtstudentid.setText(student.getStudentid());

        //Handle submit button click and show update delete button hide submit button
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lilSubmit.setVisibility(View.GONE);
                lilUpdateDelete.setVisibility(View.VISIBLE);
            }
        });

        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Show message for update attendance
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Attendance Updated", Toast.LENGTH_SHORT).show();
            }
        });

        //Show alert and message for delete attendance
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MarkAttendanceActivity.this);
                builder.setTitle("Are you sure you want to delete this attendance_list_item?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MarkAttendanceActivity.this, StudentListActivity.class);
                        Bundle b = new Bundle();
                        b.putString("TAG","MarkAttendance");
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //Handle all click of radio button selection
        View.OnClickListener radio_listener = null;
        radioPresent.setOnClickListener(radio_listener);
        radioAbsent.setOnClickListener(radio_listener);


        //set current day and date
        setCurrentDateandDay();

        //show date picker dialog
        txtDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });

        //show date picker dialog
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });



    }






    //Deselect all other radio button while selection of any one
    private void diselectOther(RadioButton radioButton) {
        RadioButton buttons[] = {radioPresent,radioAbsent};
        for (int i =0;i<buttons.length;i++)
        {
            if(buttons[i] != radioButton)
            {
                buttons[i].setChecked(false);
            }
        }
    }

    //Set current day
    private void setCurrentDateandDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTimeInMillis());
        SimpleDateFormat simpledateformatDay = new SimpleDateFormat("EEEE");
        String dayOfWeek = simpledateformatDay.format(date);
        txtDay.setText(dayOfWeek);

        SimpleDateFormat simpledateformatDate = new SimpleDateFormat("dd/MM/yyyy");
        String dateValue = simpledateformatDate.format(date);
        txtDate.setText(dateValue);

    }

    //Show date picker dialog
    private void openDatePicker() {
        final Calendar c = Calendar.getInstance();
        int  mYear = c.get(Calendar.YEAR);
        int  mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(MarkAttendanceActivity.this,
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

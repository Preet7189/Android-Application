package com.example.schooladmin.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schooladmin.R;
import com.example.schooladmin.sqlite.DatabaseHandler;
import com.example.schooladmin.ui.model.Student;


public class StudentDetailsActivity extends AppCompatActivity {
    EditText studentid, addmissionDate, fullName, grade,
            mobileNumber,address;
    Button btnDelete,btnEdit;
    ImageView ic_back;
    DatabaseHandler db;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        //Get the student object from the previous screen
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        student = (Student) bundle.getSerializable("employee");

        //Create data base object for doing database related operations
        db = new DatabaseHandler(this);

        //Get Ids of all the required fields
        studentid = (EditText) findViewById(R.id.studentid);
        addmissionDate = (EditText) findViewById(R.id.admissionDate);
        fullName = (EditText) findViewById(R.id.fullName);
        grade = (EditText) findViewById(R.id.grade);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        address = (EditText) findViewById(R.id.address);
        ic_back = (ImageView) findViewById(R.id.ic_back);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnEdit = (Button) findViewById(R.id.btnEdit);

        //Set all the value of student to related views
        studentid.setText(student.getStudentid());
        addmissionDate.setText(student.getAdmissionDate());
        fullName.setText(student.getFullName());
        grade.setText(student.getGrade());
        mobileNumber.setText(student.getMobileNo());
        address.setText(student.getAddress());

        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentDetailsActivity.this, StudentListActivity.class);
                Bundle b = new Bundle();
                b.putString("TAG","EmployeeList");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        //Handle delete button click
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Show dialog for sure delete
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentDetailsActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //delete selected student from sqlite database
                        db.deleteEmployee(student);
                        Intent intent = new Intent(StudentDetailsActivity.this, StudentListActivity.class);
                        Bundle b = new Bundle();
                        b.putString("TAG","EmployeeList");
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

        //Redirect to edit student screen and passing the student object using intent and bundle
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yourIntent = new Intent(StudentDetailsActivity.this, EditStudent.class);
                Bundle b = new Bundle();
                b.putSerializable("employee",student );
                yourIntent.putExtras(b); //pass bundle to your intent
                finish();
                startActivity(yourIntent);
            }
        });

    }
}

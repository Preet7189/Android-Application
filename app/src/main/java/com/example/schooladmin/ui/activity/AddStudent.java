package com.example.schooladmin.ui.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schooladmin.R;
import com.example.schooladmin.sqlite.DatabaseHandler;
import com.example.schooladmin.ui.model.Student;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddStudent extends AppCompatActivity {

    EditText studentid, admissionDate, fullName, grade,
            mobileNumber,address;
    Button btnAdd,btnClear;

    ImageView ic_back;
    boolean isstudentidValid, isadmissionDateValid, isfullNameValid, isgradeValid
            , ismobileNumberValid;
    TextInputLayout studentidError, admissionDateError, fullNameError, gradeError,
            mobileNumberError;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        //Create data base object for doing database related operations
        db = new DatabaseHandler(this);

        //Get Ids of all the required fields
        studentid = (EditText) findViewById(R.id.studentid);
        admissionDate = (EditText) findViewById(R.id.admissionDate);
        fullName = (EditText) findViewById(R.id.fullName);
        grade = (EditText) findViewById(R.id.grade);
        mobileNumber = (EditText) findViewById(R.id.mobileNumber);
        address = (EditText) findViewById(R.id.address);
        ic_back = (ImageView) findViewById(R.id.ic_back);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnClear = (Button) findViewById(R.id.btnClear);
        studentidError = (TextInputLayout) findViewById(R.id.studentidError);
        admissionDateError= (TextInputLayout) findViewById(R.id.admissionDateError);
        fullNameError = (TextInputLayout) findViewById(R.id.fullNameError);
        gradeError = (TextInputLayout) findViewById(R.id.gradeError);
        mobileNumberError = (TextInputLayout) findViewById(R.id.mobileNumberError);


        //Handle click on back button arrow
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Add Student
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetValidation();
            }
        });

        //Clear all the fields
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearForm();
            }
        });

        //Open date picker dialog
        admissionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int  mYear = c.get(Calendar.YEAR);
                int  mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AddStudent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                admissionDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }

    private void clearForm() {
        //Clear all the fields
        studentid.setText("");
        admissionDate.setText("");
        fullName.setText("");
        grade.setText("");
        mobileNumber.setText("");
        address.setText("");
    }

    public void SetValidation() {
        // Check for a valid name.
        if (studentid.getText().toString().isEmpty()) {
            studentidError.setError(getResources().getString(R.string.nic_error));
            isstudentidValid = false;
        } else  {
            isstudentidValid = true;
            studentidError.setErrorEnabled(false);
        }

        // Check for a valid joiningDate.
        if (admissionDate.getText().toString().isEmpty()) {
            admissionDateError.setError(getResources().getString(R.string.joining_date_error));
            isadmissionDateValid = false;
        } else  {
            isadmissionDateValid = true;
            admissionDateError.setErrorEnabled(false);
        }


        // Check for a valid fullNameError.
        if (fullName.getText().toString().isEmpty()) {
            fullNameError.setError(getResources().getString(R.string.full_name));
            isfullNameValid = false;
        } else  {
            isfullNameValid = true;
            fullNameError.setErrorEnabled(false);
        }

        // Check for a valid grade.
        if (grade.getText().toString().isEmpty()) {
            gradeError.setError(getResources().getString(R.string.designation_error));
            isgradeValid = false;
        } else  {
            isgradeValid = true;
            gradeError.setErrorEnabled(false);
        }

        // Check for a valid mobileNumber.
        if (mobileNumber.getText().toString().isEmpty()) {
            mobileNumberError.setError(getResources().getString(R.string.mobile_number_error));
            ismobileNumberValid = false;
        } else  {
            ismobileNumberValid = true;
            mobileNumberError.setErrorEnabled(false);
        }



        //If all fields are valid then add the new student inside data base using database object
        if (isstudentidValid && isadmissionDateValid && isfullNameValid && isgradeValid
                && ismobileNumberValid) {

            db.addEmployee(new Student(studentid.getText().toString(), admissionDate.getText().toString()
            ,fullName.getText().toString(),grade.getText().toString(),mobileNumber.getText().toString(),
                    address.getText().toString()));

            Toast.makeText(getApplicationContext(), "Student added Successfully", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

}

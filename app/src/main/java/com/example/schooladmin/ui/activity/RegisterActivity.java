package com.example.schooladmin.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schooladmin.MainActivity;
import com.example.schooladmin.R;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, phone, password,schoolName,grade,address,schoolRegNo;
    Button register;
    TextView login;
    boolean isNameValid, isEmailValid, isPhoneValid, isPasswordValid, isschoolValid,isgradeValid,isAddressValid;
    TextInputLayout nameError, emailError, phoneError, passError,schoolNameError,gradeError,addressError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Get Ids of all the required view
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        password = (EditText) findViewById(R.id.password);
        schoolName = (EditText) findViewById(R.id.schoolName);
        grade = (EditText) findViewById(R.id.grade);
        address = (EditText) findViewById(R.id.address);
        login = (TextView) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        nameError = (TextInputLayout) findViewById(R.id.nameError);
        emailError = (TextInputLayout) findViewById(R.id.emailError);
        phoneError = (TextInputLayout) findViewById(R.id.phoneError);
        passError = (TextInputLayout) findViewById(R.id.passError);
        schoolNameError = (TextInputLayout) findViewById(R.id.schoolNameError);
        gradeError = (TextInputLayout) findViewById(R.id.gradeError);
        addressError = (TextInputLayout) findViewById(R.id.addressError);

        //Click on register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check for the validation of all the required fields
                SetValidation();
            }
        });

        //Click on login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to LoginActivity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SetValidation() {
        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid phone number.
        if (phone.getText().toString().isEmpty()) {
            phoneError.setError(getResources().getString(R.string.phone_error));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
            phoneError.setErrorEnabled(false);
        }

        // Check for a valid password.
        if (password.getText().toString().isEmpty()) {
            passError.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (password.getText().length() < 6) {
            passError.setError(getResources().getString(R.string.error_invalid_password));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            passError.setErrorEnabled(false);
        }

        // Check for a valid school name.
        if (schoolName.getText().toString().isEmpty()) {
            schoolNameError.setError(getResources().getString(R.string.company_error));
            isschoolValid = false;
        } else  {
            isschoolValid = true;
            schoolNameError.setErrorEnabled(false);
        }
        // Check for a valid grade.
        if (grade.getText().toString().isEmpty()) {
            gradeError.setError(getResources().getString(R.string.designation_error));
            isgradeValid = false;
        } else  {
            isgradeValid = true;
            gradeError.setErrorEnabled(false);
        }
        // Check for a valid address.
        if (address.getText().toString().isEmpty()) {
            addressError.setError(getResources().getString(R.string.address_error));
            isAddressValid = false;
        } else  {
            isAddressValid = true;
            addressError.setErrorEnabled(false);
        }

        //Check for all the fields validation if all are checked then make register success
        if (isNameValid && isEmailValid && isPhoneValid && isPasswordValid && isschoolValid && isgradeValid && isAddressValid) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

    }

}
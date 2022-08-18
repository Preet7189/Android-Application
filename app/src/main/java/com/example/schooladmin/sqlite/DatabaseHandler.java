package com.example.schooladmin.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.schooladmin.ui.model.Student;

import java.util.ArrayList;


public class DatabaseHandler  extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "employeeManager";
    private static final String TABLE_EMPLOYEE = "employee";

    private static final String KEY_ID = "id";
    private static final String KEY_STUDENT_ID =  "studentid";
    private static final String KEY_ADMISSION_DATE = "admissionDate";
    private static final String KEY_FULL_NAME ="fullName";
    private static final String KEY_GRADE ="grade";
    private static final String KEY_MOBILE_NO ="MobileNo";
    private static final String KEY_ADDRESS ="Address";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_STUDENT_ID + " TEXT,"
                + KEY_ADMISSION_DATE + " TEXT,"
                + KEY_FULL_NAME + " TEXT,"
                + KEY_GRADE + " TEXT,"
                + KEY_MOBILE_NO + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);

        // Create tables again
        onCreate(db);
    }

    // code to add the new student
    public  void addEmployee(Student employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_ID, employee.getStudentid()); // Contact Name
        values.put(KEY_ADMISSION_DATE, employee.getAdmissionDate()); // Contact Phone
        values.put(KEY_FULL_NAME, employee.getFullName()); // Contact Phone
        values.put(KEY_GRADE, employee.getGrade()); // Contact Phone
        values.put(KEY_MOBILE_NO, employee.getMobileNo()); // Contact Phone
        values.put(KEY_ADDRESS, employee.getAddress()); // Contact Phone


        // Inserting Row
        db.insert(TABLE_EMPLOYEE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single student
    public Student getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EMPLOYEE, new String[] { KEY_ID,
                        KEY_STUDENT_ID, KEY_ADMISSION_DATE, KEY_FULL_NAME
                        , KEY_GRADE, KEY_MOBILE_NO
                        , KEY_ADDRESS}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student employee = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)
                , cursor.getString(5), cursor.getString(6));
        // return contact
        return employee;
    }

    // code to get all student in a list view
    public ArrayList<Student> getAllEmployee() {
        ArrayList<Student> employeeList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Student employee = new Student();
                employee.set_id(Integer.parseInt(cursor.getString(0)));
                employee.setStudentid(cursor.getString(1));
                employee.setAdmissionDate(cursor.getString(2));
                employee.setFullName(cursor.getString(3));
                employee.setGrade(cursor.getString(4));
                employee.setMobileNo(cursor.getString(5));
                employee.setAddress(cursor.getString(6));
                // Adding student to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return student list
        return employeeList;
    }

    // code to update the single student
    public int updateEmployee(Student employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        Log.e("Inside Update","Name is"+employee.getFullName()+"Id="+employee.get_id());

        values.put(KEY_STUDENT_ID, employee.getStudentid()); // Contact Name
        values.put(KEY_ADMISSION_DATE, employee.getAdmissionDate()); // Contact Phone
        values.put(KEY_FULL_NAME, employee.getFullName()); // Contact Phone
        values.put(KEY_GRADE, employee.getGrade()); // Contact Phone
        values.put(KEY_MOBILE_NO, employee.getMobileNo()); // Contact Phone
        values.put(KEY_ADDRESS, employee.getAddress()); // Contact Phone



        // updating row
        return db.update(TABLE_EMPLOYEE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(employee.get_id()) });
    }

    // Deleting single student
    public void deleteEmployee(Student employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE, KEY_ID + " = ?",
                new String[] { String.valueOf(employee.get_id()) });
        db.close();
    }

    // Getting student Count
    public int getEmployeeCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
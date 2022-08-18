package com.example.schooladmin.ui.model;

import java.io.Serializable;

//Make class Serializable for passing object of this class via intent using bundle
public class Student implements Serializable {
    int _id;
    String studentid;
    String admissionDate;
    String fullName;
    String grade;
    String MobileNo;
    String Address;




    //add constructor with parameters and ID
    public Student(int id, String studentid, String admissionDate, String fullName, String grade
    , String MobileNo, String Address){
        this._id = id;
        this.studentid = studentid;
        this.admissionDate = admissionDate;
        this.fullName = fullName;
        this.grade = grade;
        this.MobileNo = MobileNo;
        this.Address = Address;

    }
    //add constructor with parameters without ID
    public Student(String studentid, String admissionDate, String fullName, String grade
            , String MobileNo, String Address){
        this.studentid = studentid;
        this.admissionDate = admissionDate;
        this.fullName = fullName;
        this.grade = grade;
        this.MobileNo = MobileNo;
        this.Address = Address;
    }



    public Student() {

    }
    //Generate getters and setters

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {this.grade = grade; }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }



    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


}

package com.example.schooladmin.ui.model;

public class Attendance {
    String date;
    String pay;
    //add constructor with parameters
    public Attendance(String date, String pay){
        this.date = date;
        this.pay = pay;
    }
    //Generate getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}

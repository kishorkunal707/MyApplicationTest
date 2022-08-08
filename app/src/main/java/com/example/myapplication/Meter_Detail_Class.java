package com.example.myapplication;

public class Meter_Detail_Class {

    private String meter_Make;
    private String serial_no;

// constructor for DataSnapshot
    public Meter_Detail_Class() {
    }

    // Constructor for variable initialization

    public Meter_Detail_Class(String meter_Make, String serialNo_ACT) {
        this.meter_Make = meter_Make;
        this.serial_no = serialNo_ACT;
    }

    // Getter & Setters


    public String getMeter_Make() {
        return meter_Make;
    }

    public void setMeter_Make(String meter_Make) {
        this.meter_Make = meter_Make;
    }

    public String getSerial_no() {
        return serial_no;
    }

    public void setSerial_no(String serial_no) {
        this.serial_no = serial_no;
    }
}

package com.deguzman.exam2;

public class Student {
    //Long id;
    String fname, lname;
    Long average;

    public Student(String fname, String lname, Long average) {
        this.fname = fname;
        this.lname = lname;
        this.average = average;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public Long getAverage() {
        return average;
    }

}
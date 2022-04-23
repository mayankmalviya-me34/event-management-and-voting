package com.example.minorproject.Models;

public class StudentInfoForm {
    String name,enrollment,dept,branch,address,gender,mobile,email,acedemicYear,linkedIn;

    public StudentInfoForm(String name, String enrollment, String dept, String branch, String address, String gender, String mobile, String email, String acedemicYear,String linkedIn) {
        this.name = name;
        this.enrollment = enrollment;
        this.dept = dept;
        this.branch = branch;
        this.address = address;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
        this.linkedIn = linkedIn;
        this.acedemicYear = acedemicYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcedemicYear() {
        return acedemicYear;
    }

    public void setAcedemicYear(String acedemicYear) {
        this.acedemicYear = acedemicYear;
    }
}

package com.example.minorproject.Models;

public class FacultyModel {
    private  String   Email, Password, userID;
    private String newEmail,newID,newName,newDept,newPhno,facultyOf;

    public FacultyModel() {

    }

    public FacultyModel(String newEmail, String newID, String newName, String newDept, String newPhno, String facultyOf) {
        this.newEmail = newEmail;
        this.newID = newID;
        this.newName = newName;
        this.newDept = newDept;
        this.newPhno = newPhno;
        this.facultyOf = facultyOf;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getNewID() {
        return newID;
    }

    public void setNewID(String newID) {
        this.newID = newID;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewDept() {
        return newDept;
    }

    public void setNewDept(String newDept) {
        this.newDept = newDept;
    }

    public String getNewPhno() {
        return newPhno;
    }

    public void setNewPhno(String newPhno) {
        this.newPhno = newPhno;
    }

    public String getFacultyOf() {
        return facultyOf;
    }

    public void setFacultyOf(String facultyOf) {
        this.facultyOf = facultyOf;
    }

    public FacultyModel(String email, String password, String userID) {
        Email = email;
        Password = password;
        this.userID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}

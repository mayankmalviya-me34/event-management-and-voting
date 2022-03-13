package com.example.minorproject.Models;

public class FacultyModel {
    private  String   Email, Password, userID;

    public FacultyModel() {

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

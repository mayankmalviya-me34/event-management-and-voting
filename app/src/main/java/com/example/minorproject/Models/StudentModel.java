package com.example.minorproject.Models;

public class StudentModel {
    String Name, Enroll, Email, Password, userID, profilePic ,participateTime,eventID;



    public StudentModel(String name, String enroll, String userID, String participateTime, String eventID) {
        Name = name;
        Enroll = enroll;
        this.userID = userID;
        this.participateTime = participateTime;
        this.eventID = eventID;
    }

    public String getParticipateTime() {
        return participateTime;
    }

    public void setParticipateTime(String participateTime) {
        this.participateTime = participateTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEnroll() {
        return Enroll;
    }

    public void setEnroll(String enroll) {
        Enroll = enroll;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
}

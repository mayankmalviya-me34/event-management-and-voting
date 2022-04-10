package com.example.minorproject.AfterSelectedCategories;

public class CategoriesEvent {
    String EvtPhoto,EvtTitle,EvtDescription, EvtDepartment,EvtOrganizerName,EvtOrganizerNumber,EvtAddress, EvtDate, EvtTime,CurrentDateTime,FacultyID,lastDate,eligibility,isLive,evtCatg;

    public CategoriesEvent(String evtPhoto, String evtTitle, String evtDescription, String evtDepartment, String evtOrganizerName, String evtOrganizerNumber, String evtAddress, String evtDate, String evtTime, String currentDateTime, String facultyID, String lastDate, String eligibility,String isLive,String evtCatg) {
        EvtPhoto = evtPhoto;
        EvtTitle = evtTitle;
        EvtDescription = evtDescription;
        EvtDepartment = evtDepartment;
        EvtOrganizerName = evtOrganizerName;
        EvtOrganizerNumber = evtOrganizerNumber;
        EvtAddress = evtAddress;
        EvtDate = evtDate;
        EvtTime = evtTime;
        CurrentDateTime = currentDateTime;
        FacultyID = facultyID;
        this.lastDate = lastDate;
        this.eligibility = eligibility;
        this.isLive = isLive;
        this.evtCatg = evtCatg;

    }

    public CategoriesEvent(String evtPhoto, String evtTitle, String evtDepartment, String evtOrganizerName, String evtOrganizerNumber, String evtAddress, String evtTime, String facultyID) {
        EvtPhoto = evtPhoto;
        EvtTitle = evtTitle;
        EvtDepartment = evtDepartment;
        EvtOrganizerName = evtOrganizerName;
        EvtOrganizerNumber = evtOrganizerNumber;
        EvtAddress = evtAddress;
        EvtTime = evtTime;
        FacultyID = facultyID;
    }

    public String getEvtCatg() {
        return evtCatg;
    }

    public void setEvtCatg(String evtCatg) {
        this.evtCatg = evtCatg;
    }

    public String getIsLive() {
        return isLive;
    }

    public void setIsLive(String isLive) {
        this.isLive = isLive;
    }

    public String getFacultyID() {
        return FacultyID;
    }

    public void setFacultyID(String facultyID) {
        FacultyID = facultyID;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getEvtPhoto() {
        return EvtPhoto;
    }

    public void setEvtPhoto(String evtPhoto) {
        EvtPhoto = evtPhoto;
    }

    public String getEvtTitle() {
        return EvtTitle;
    }

    public void setEvtTitle(String evtTitle) {
        EvtTitle = evtTitle;
    }

    public String getEvtDescription() {
        return EvtDescription;
    }

    public void setEvtDescription(String evtDescription) {
        EvtDescription = evtDescription;
    }

    public String getEvtDepartment() {
        return EvtDepartment;
    }

    public void setEvtDepartment(String evtDepartment) {
        EvtDepartment = evtDepartment;
    }

    public String getEvtOrganizerName() {
        return EvtOrganizerName;
    }

    public void setEvtOrganizerName(String evtOrganizerName) {
        EvtOrganizerName = evtOrganizerName;
    }

    public String getEvtOrganizerNumber() {
        return EvtOrganizerNumber;
    }

    public void setEvtOrganizerNumber(String evtOrganizerNumber) {
        EvtOrganizerNumber = evtOrganizerNumber;
    }

    public String getEvtAddress() {
        return EvtAddress;
    }

    public void setEvtAddress(String evtAddress) {
        EvtAddress = evtAddress;
    }

    public String getEvtDate() {
        return EvtDate;
    }

    public void setEvtDate(String evtDate) {
        EvtDate = evtDate;
    }

    public String getEvtTime() {
        return EvtTime;
    }

    public void setEvtTime(String evtTime) {
        EvtTime = evtTime;
    }

    public String getCurrentDateTime() {
        return CurrentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        CurrentDateTime = currentDateTime;
    }
}

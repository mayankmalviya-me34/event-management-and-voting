package com.example.minorproject.AfterSelectedCategories;

public class CategoriesEvent {
    String EvtTitle, EvtDepartment, EvtTime;

    public CategoriesEvent(String evtTitle, String evtDepartment, String evtTime) {
        EvtTitle = evtTitle;
        EvtDepartment = evtDepartment;
        EvtTime = evtTime;
    }

    public String getEvtTitle() {
        return EvtTitle;
    }

    public void setEvtTitle(String evtTitle) {
        EvtTitle = evtTitle;
    }

    public String getEvtDepartment() {
        return EvtDepartment;
    }

    public void setEvtDepartment(String evtDepartment) {
        EvtDepartment = evtDepartment;
    }

    public String getEvtTime() {
        return EvtTime;
    }

    public void setEvtTime(String evtTime) {
        EvtTime = evtTime;
    }
}

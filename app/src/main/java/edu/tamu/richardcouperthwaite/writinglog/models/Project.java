package edu.tamu.richardcouperthwaite.writinglog.models;


import java.io.Serializable;

public class Project implements Serializable {
    String ID;
    String Title;
    String Overview;
    String Lastcomment;
    int Timespent;
    String dueDate;

    public Project(String ID, String title, String overview, String lastcomment, int timespent, String dueDate) {
        this.ID = ID;
        Title = title;
        Overview = overview;
        Lastcomment = lastcomment;
        Timespent = timespent;
        this.dueDate = dueDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }

    public String getLastcomment() {
        return Lastcomment;
    }

    public void setLastcomment(String lastcomment) {
        Lastcomment = lastcomment;
    }

    public int getTimespent() {
        return Timespent;
    }

    public void setTimespent(int timespent) {
        Timespent = timespent;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


}

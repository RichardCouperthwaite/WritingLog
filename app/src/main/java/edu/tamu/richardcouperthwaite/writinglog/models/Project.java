package edu.tamu.richardcouperthwaite.writinglog.models;

import java.io.Serializable;

public class Project implements Serializable {

    String id;
    String title;
    String overview;
    String lastcomment;
    int timespent;
    String duedate;

    public Project(String id, String title, String overview, String lastcomment, int timespent, String duedate) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.lastcomment = lastcomment;
        this.timespent = timespent;
        this.duedate = duedate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getLastcomment() {
        return lastcomment;
    }

    public void setLastcomment(String lastcomment) {
        this.lastcomment = lastcomment;
    }

    public int getTimespent() {
        return timespent;
    }

    public void setTimespent(int timespent) {
        this.timespent = timespent;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
}

package edu.tamu.richardcouperthwaite.writinglog.models;

import java.util.ArrayList;
import java.util.Date;

public class project {
    String Title;
    String Description;
    int totalTime;
    Date duedate;
    String lastComment;
    ArrayList allComments;

    public project(String title, String description, int totalTime, Date duedate, String lastComment, ArrayList allComments) {
        this.Title = title;
        this.Description = description;
        this.totalTime = totalTime;
        this.duedate = duedate;
        this.lastComment = lastComment;
        this.allComments = allComments;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public String getLastComment() {
        return lastComment;
    }

    public void setLastComment(String lastComment) {
        this.lastComment = lastComment;
    }

    public ArrayList getAllComments() {
        return allComments;
    }

    public void setAllComments(ArrayList allComments) {
        this.allComments = allComments;
    }
}

package edu.tamu.richardcouperthwaite.writinglog.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "projects")
public class Project {
    @PrimaryKey(autoGenerate = true)
    private int projID;

    @ColumnInfo(name = "Title")
    private String projectTitle;

    @ColumnInfo(name = "Description")
    private String projectDescription;

    @ColumnInfo(name = "Time")
    private int projecttime;

    @ColumnInfo(name = "LastComment")
    private String projectLastComment;

    @ColumnInfo(name = "DueDate")
    private String projectDueDate;



    public int getProjID() {
        return projID;
    }

    public void setProjID(int projID) {
        this.projID = projID;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public int getProjecttime() {
        return projecttime;
    }

    public void setProjecttime(int projecttime) {
        this.projecttime = projecttime;
    }

    public String getProjectLastComment() {
        return projectLastComment;
    }

    public void setProjectLastComment(String projectLastComment) {
        this.projectLastComment = projectLastComment;
    }

    public String getProjectDueDate() {
        return projectDueDate;
    }

    public void setProjectDueDate(String projectDueDate) {
        this.projectDueDate = projectDueDate;
    }
}

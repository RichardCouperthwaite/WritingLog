package edu.tamu.richardcouperthwaite.writinglog.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "project_list")
public class Project implements Serializable{

    @PrimaryKey
    @NonNull
    @ColumnInfo(typeAffinity = 3)
    public int ID;

    @ColumnInfo(name = "project_title", typeAffinity = 2)
    public String Name;
    @ColumnInfo(name = "elapsed_time", typeAffinity = 3)
    public int Time;
    @ColumnInfo(name = "project_description", typeAffinity = 2)
    public String Description;
    @ColumnInfo(name = "last_comment", typeAffinity = 2)
    public String LastComment;

    @NonNull
    public int getID() {
        return ID;
    }

    public void setID(@NonNull int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLastComment() {
        return LastComment;
    }

    public void setLastComment(String lastComment) {
        LastComment = lastComment;
    }

    public Project(int ID, String Name, int Time, String Description, String LastComment) {
        this.ID = ID;
        this.Name = Name;
        this.Time = Time;
        this.Description = Description;
        this.LastComment = LastComment;
    }
}

package edu.tamu.richardcouperthwaite.writinglog.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "session_log")
public class Session {
    @PrimaryKey
    @ColumnInfo(name = "Session_ID")
    int SessionID;

    public int getSessionID() { return SessionID; }

    public void setSessionID(int sessionID) { SessionID = sessionID; }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getEnd() {
        return End;
    }

    public void setEnd(String end) {
        End = end;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String project) {
        Project = project;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    String Date;
    String Start;
    String End;
    String Total;
    String Project;
    String Comments;

    public Session(int SessionID, String Date, String Start, String End, String Total, String Project, String Comments) {
        this.SessionID = SessionID;
        this.Date = Date;
        this.Start = Start;
        this.End = End;
        this.Total = Total;
        this.Project = Project;
        this.Comments = Comments;
    }
}
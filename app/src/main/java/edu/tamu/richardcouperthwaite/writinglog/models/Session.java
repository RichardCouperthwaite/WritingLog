package edu.tamu.richardcouperthwaite.writinglog.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "session_log")
public class Session {
    @PrimaryKey
    @ColumnInfo(name = "Session_ID")
    private int SessionID;

    private String Date;
    private String Start;
    private String End;
    private String Total;
    private String Project;
    private String Comments;

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
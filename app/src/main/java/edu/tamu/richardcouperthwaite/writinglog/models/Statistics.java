package edu.tamu.richardcouperthwaite.writinglog.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "writing_stats")
public class Statistics {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "stat_title", typeAffinity = 2)
    private String Title;

    @ColumnInfo(name = "value", typeAffinity = 2)
    private String Value;

    public Statistics(@NonNull String Title, String Value) {
        this.Title = Title;
        this.Value = Value;
    }

    @NonNull
    public String getTitle() { return Title; }

    public void setTitle(@NonNull String title) { Title = title; }

    public String getValue() { return Value; }

    public void setValue(String value) { Value = value; }
}
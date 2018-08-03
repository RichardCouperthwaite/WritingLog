package edu.tamu.richardcouperthwaite.writinglog.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Statistics")
public class statistics {
    @PrimaryKey
    private String statName;

    @ColumnInfo
    int statValue;

    public void setStatName(String name) {this.statName = name;}

    public String getStatName() {return this.statName;}

    public void setStatValue(int value) {this.statValue = value;}

    public int getStatValue() {return this.statValue;}
}

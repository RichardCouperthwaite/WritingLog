package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;


@Dao
public interface statsDao {
    @Insert
    void insert(Statistics statistics);

    @Query("DELETE FROM writing_stats")
    void deleteAll();

    @Query("SELECT * from writing_stats")
    LiveData<List<Statistics>> getAllStats();

    @Update
    void updateStats(Statistics statistics);
}

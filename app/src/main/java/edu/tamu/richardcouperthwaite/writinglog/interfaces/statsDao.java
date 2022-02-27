package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

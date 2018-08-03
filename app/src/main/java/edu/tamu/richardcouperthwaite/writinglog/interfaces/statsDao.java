package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.activities.Statistics;

@Dao
public interface statsDao {
    @Insert
    void insert(Statistics Stats);

    @Query("DELETE FROM Statistics")
    void deleteAll();

    @Query("SELECT * from Statistics ORDER BY statName ASC")
    LiveData<List<Statistics>> getAllStatistics();
}

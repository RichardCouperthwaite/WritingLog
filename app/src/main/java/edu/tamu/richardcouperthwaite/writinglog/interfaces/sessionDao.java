package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.models.SessionLogItems;

@Dao
public interface sessionDao {
    @Insert
    void insert(SessionLogItems sessionLogItems);

    @Query("DELETE FROM SessionLog")
    void deleteAll();

    @Query("SELECT * from sessionlog ORDER BY date ASC")
    LiveData<List<SessionLogItems>> getAllSession();
}

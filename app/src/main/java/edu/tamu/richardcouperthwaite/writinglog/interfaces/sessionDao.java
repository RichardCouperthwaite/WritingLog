package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.models.Session;

@Dao
public interface sessionDao {
    @Insert
    void insert(Session session);

    @Query("DELETE FROM session_log")
    void deleteAll();

    @Query("SELECT * from session_log ORDER BY Session_ID ASC")
    LiveData<List<Session>> getAllSessions();
}

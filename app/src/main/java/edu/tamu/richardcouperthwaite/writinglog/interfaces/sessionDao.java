package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

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

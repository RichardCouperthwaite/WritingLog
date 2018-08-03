package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.models.Project;

@Dao
public interface projectDao {

    @Insert
    void insert(Project project);

    @Query("DELETE FROM projects")
    void deleteAll();

    @Query("SELECT * from projects ORDER BY projID ASC")
    LiveData<List<Project>> getallProjects();

}

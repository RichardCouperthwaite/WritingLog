package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.models.Project;

@Dao
public interface ProjectDao {

    @Insert
    void insert(Project project);

    @Query("DELETE FROM project_list")
    void deleteAll();

    @Query("SELECT * from project_list ORDER BY project_title ASC")
    LiveData<List<Project>> getAllProjects();
}

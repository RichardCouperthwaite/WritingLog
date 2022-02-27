package edu.tamu.richardcouperthwaite.writinglog.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Update
    void updateProject(Project project);

    @Delete
    void deleteProject(Project project);
}

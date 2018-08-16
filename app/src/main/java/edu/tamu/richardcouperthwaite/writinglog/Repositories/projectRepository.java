package edu.tamu.richardcouperthwaite.writinglog.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.Database.WritingLogDatabase;
import edu.tamu.richardcouperthwaite.writinglog.interfaces.ProjectDao;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;

public class projectRepository {
    private ProjectDao mprojectDao;
    private LiveData<List<Project>> projectList;

    public projectRepository(Application application) {
        WritingLogDatabase db = WritingLogDatabase.getDatabase(application);
        mprojectDao = db.projectDao();
        projectList = mprojectDao.getAllProjects();
    }

    public LiveData<List<Project>> getProjectList() {return projectList; }

    public void insert (Project project) {new insertAsyncTask(mprojectDao).execute(project); }

    private static class insertAsyncTask extends AsyncTask<Project, Void, Void> {
        private ProjectDao AsyncProjectDao;

        insertAsyncTask(ProjectDao dao) {AsyncProjectDao = dao; }

        @Override
        protected Void doInBackground(final Project... params) {
            AsyncProjectDao.insert(params[0]);
            return null;
        }
    }
}

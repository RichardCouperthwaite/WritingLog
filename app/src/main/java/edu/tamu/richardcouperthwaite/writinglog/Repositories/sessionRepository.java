package edu.tamu.richardcouperthwaite.writinglog.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.Database.WritingLogDatabase;
import edu.tamu.richardcouperthwaite.writinglog.interfaces.sessionDao;
import edu.tamu.richardcouperthwaite.writinglog.models.Session;

public class sessionRepository {
    private sessionDao msessionDao;
    private LiveData<List<Session>> sessionList;

    public sessionRepository(Application application) {
        WritingLogDatabase db = WritingLogDatabase.getDatabase(application);
        msessionDao = db.sessionDao();
        sessionList = msessionDao.getAllSessions();
    }

    public LiveData<List<Session>> getSessionList() {return sessionList; }

    public void insert (Session session) {new insertAsyncTask(msessionDao).execute(session); }

    private static class insertAsyncTask extends AsyncTask<Session, Void, Void> {
        private sessionDao AsyncSessionDao;

        insertAsyncTask(sessionDao dao) {AsyncSessionDao = dao; }

        @Override
        protected Void doInBackground(final Session... params) {
            AsyncSessionDao.insert(params[0]);
            return null;
        }
    }
}

package edu.tamu.richardcouperthwaite.writinglog.Repositories;
import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.Database.WritingLogDatabase;
import edu.tamu.richardcouperthwaite.writinglog.interfaces.statsDao;
import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;

public class statsRepository {
    private statsDao mstatsDao;
    private LiveData<List<Statistics>> statsList;

    public statsRepository(Application application) {
        WritingLogDatabase db = WritingLogDatabase.getDatabase(application);
        mstatsDao = db.statDao();
        statsList = mstatsDao.getAllStats();
    }

    public LiveData<List<Statistics>> getProjectList() {return statsList; }

    public void insert (Statistics stat) {new insertAsyncTask(mstatsDao).execute(stat); }

    public void update (Statistics stat) {new updateAsyncTask(mstatsDao).execute(stat); }

    private static class insertAsyncTask extends AsyncTask<Statistics, Void, Void> {
        private statsDao AsyncStatsDao;

        insertAsyncTask(statsDao dao) {AsyncStatsDao = dao; }

        @Override
        protected Void doInBackground(final Statistics... params) {
            AsyncStatsDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Statistics, Void, Void> {
        private statsDao AsyncStatsDao;

        updateAsyncTask(statsDao dao) {AsyncStatsDao = dao; }

        @Override
        protected Void doInBackground(final Statistics... params) {
            AsyncStatsDao.updateStats(params[0]);
            return null;
        }
    }
}

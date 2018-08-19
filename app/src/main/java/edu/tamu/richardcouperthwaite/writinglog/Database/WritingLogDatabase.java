package edu.tamu.richardcouperthwaite.writinglog.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.interfaces.ProjectDao;
import edu.tamu.richardcouperthwaite.writinglog.interfaces.sessionDao;
import edu.tamu.richardcouperthwaite.writinglog.interfaces.statsDao;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.Session;
import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;


@Database(entities = {Project.class, Statistics.class, Session.class}, version = 3, exportSchema = false)
public abstract class WritingLogDatabase extends RoomDatabase {
    public abstract ProjectDao projectDao();
    public abstract statsDao statDao();
    public abstract sessionDao sessionDao();
    private static String firstdayofmonth;
    private static String firstdayofweek;

    private static WritingLogDatabase INSTANCE;

    public static WritingLogDatabase getDatabase(final Context context) {
        Calendar calendar = GregorianCalendar.getInstance();
        firstdayofmonth = String.format("%d/01/%d", calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        int Dayofweek = (calendar.get(Calendar.DAY_OF_WEEK)-1)*(-1);
        calendar.add(Calendar.DATE, Dayofweek);
        firstdayofweek = String.format("%d/%d/%d", calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.YEAR));
        if (INSTANCE == null) {
            synchronized (WritingLogDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WritingLogDatabase.class, "writingLog_Database")
                        .addCallback(sWritingLogDatabaseCallback)
                        .build();
            }
        }
        return INSTANCE;
    }

    private static Callback sWritingLogDatabaseCallback =
            new Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDBAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDBAsync extends AsyncTask<Void, Void, Void> {
        private final ProjectDao projDao;
        private final statsDao statDao;
        private final sessionDao sessionDao;

        PopulateDBAsync(WritingLogDatabase db) {
            projDao = db.projectDao();
            statDao = db.statDao();
            sessionDao = db.sessionDao();
        }

        @Override
        protected Void doInBackground(final Void... params)  {
            /*if (statDao.getAllStats() == null) {
                statDao.insert(new Statistics("CurrentWeekTime", "0"));
                statDao.insert(new Statistics("CurrentWeekDays", "0,0,0,0,0,0,0"));
                statDao.insert(new Statistics("PreviousWeekTime", "0"));
                statDao.insert(new Statistics("PreviousWeekDays", "0"));
                statDao.insert(new Statistics("CurrentMonthTime", "0"));
                statDao.insert(new Statistics("CurrentMonthDays", "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0"));
                statDao.insert(new Statistics("PreviousMonthTime", "0"));
                statDao.insert(new Statistics("PreviousMonthDays", "0"));
                statDao.insert(new Statistics("CurrentDIM", "0"));
                statDao.insert(new Statistics("PreviousDIM", "0"));
                statDao.insert(new Statistics("WeekStart", firstdayofweek));
                statDao.insert(new Statistics("MonthStart", firstdayofmonth));
            } */
            statDao.updateStats(new Statistics("CurrentWeekTime", "0"));
            return null;
        }


    }
}

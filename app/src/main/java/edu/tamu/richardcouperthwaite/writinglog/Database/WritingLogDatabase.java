package edu.tamu.richardcouperthwaite.writinglog.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import edu.tamu.richardcouperthwaite.writinglog.interfaces.ProjectDao;
import edu.tamu.richardcouperthwaite.writinglog.interfaces.sessionDao;
import edu.tamu.richardcouperthwaite.writinglog.interfaces.statsDao;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.Session;
import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;


@Database(entities = {Project.class, Statistics.class, Session.class}, version = 2, exportSchema = false)
public abstract class WritingLogDatabase extends RoomDatabase {
    public abstract ProjectDao projectDao();
    public abstract statsDao statDao();
    public abstract sessionDao sessionDao();

    private static WritingLogDatabase INSTANCE;

    public static WritingLogDatabase getDatabase(final Context context) {
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
            projDao.deleteAll();
            Project newProject = new Project(1,"Test Project 1", 110, "This is a test project", "No Previous comments");
            projDao.insert(newProject);
            newProject = new Project(2,"Test Project 2", 120, "This is a test project", "No Previous comments");
            projDao.insert(newProject);
            newProject = new Project(3,"Test Project 3", 130, "This is a test project", "No Previous comments");
            projDao.insert(newProject);

            statDao.deleteAll();
            Statistics newStat = new Statistics("CurrWeekTime", "20");
            statDao.insert(newStat);
            newStat = new Statistics("CurrWeekDays", "4");
            statDao.insert(newStat);
            newStat = new Statistics("PrevWeekTime", "40");
            statDao.insert(newStat);
            newStat = new Statistics("PrevWeekDays", "6");
            statDao.insert(newStat);

            return null;
        }


    }
}

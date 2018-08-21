package edu.tamu.richardcouperthwaite.writinglog.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    private static WritingLogDatabase INSTANCE;

    public static WritingLogDatabase getDatabase(final Context context) {
        Calendar calendar = GregorianCalendar.getInstance();
        int Dayofweek = (calendar.get(Calendar.DAY_OF_WEEK)-1)*(-1);
        calendar.add(Calendar.DATE, Dayofweek);
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
                }
            };
}

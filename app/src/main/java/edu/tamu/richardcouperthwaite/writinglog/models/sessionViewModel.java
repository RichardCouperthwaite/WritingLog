package edu.tamu.richardcouperthwaite.writinglog.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;
import edu.tamu.richardcouperthwaite.writinglog.Repositories.sessionRepository;


public class sessionViewModel extends AndroidViewModel {
    private sessionRepository mRepository;
    private LiveData<List<Session>> mSessionList;

    public sessionViewModel(Application application) {
        super(application);
        mRepository = new sessionRepository(application);
        mSessionList = mRepository.getSessionList();
    }

    public LiveData<List<Session>> getSessionList() { return mSessionList; }

    public void insert(Session session) { mRepository.insert(session); }
}

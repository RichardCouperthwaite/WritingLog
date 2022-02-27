package edu.tamu.richardcouperthwaite.writinglog.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.Repositories.statsRepository;

public class statViewModel extends AndroidViewModel {
    private statsRepository mRepository;
    private LiveData<List<Statistics>> mStatList;

    public statViewModel(Application application) {
        super(application);
        mRepository = new statsRepository(application);
        mStatList = mRepository.getProjectList();
    }

    public LiveData<List<Statistics>> getStatList() { return mStatList; }

    public void insert(Statistics stats) { mRepository.insert(stats); }

    public void update(Statistics stats) { mRepository.update(stats); }
}

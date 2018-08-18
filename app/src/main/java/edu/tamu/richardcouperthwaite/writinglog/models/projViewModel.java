package edu.tamu.richardcouperthwaite.writinglog.models;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.Repositories.projectRepository;


public class projViewModel extends AndroidViewModel {
    private projectRepository mRepository;
    private LiveData<List<Project>> mProjectList;

    public projViewModel(Application application) {
        super(application);
        mRepository = new projectRepository(application);
        mProjectList = mRepository.getProjectList();
    }

    public LiveData<List<Project>> getProjectList() { return mProjectList; }

    public void insert(Project project) { mRepository.insert(project); }

    public void update(Project project) { mRepository.update(project); }

    public void delete(Project project) { mRepository.delete(project); }
}

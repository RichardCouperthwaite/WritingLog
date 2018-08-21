package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.List;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.adapters.projectListAdapter;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.projViewModel;

public class ProjectList extends AppCompatActivity {

    RecyclerView recyclerView;
    private projViewModel mprojViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        mprojViewModel = ViewModelProviders.of(this).get(projViewModel.class);

        recyclerView = findViewById(R.id.rvprojectlist);
        final projectListAdapter adapter = new projectListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mprojViewModel.getProjectList().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                adapter.setProjectList(projects, mprojViewModel);
            }
        });
    }
}

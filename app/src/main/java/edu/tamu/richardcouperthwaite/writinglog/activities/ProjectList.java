package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.databinding.ActivityProjectListBinding;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.projViewModel;
import edu.tamu.richardcouperthwaite.writinglog.adapters.projectListAdapter;
import edu.tamu.richardcouperthwaite.writinglog.R;

public class ProjectList extends AppCompatActivity {
    private ActivityProjectListBinding binding;
    RecyclerView recyclerView;
    private projViewModel mprojViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProjectListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mprojViewModel = new ViewModelProvider(this).get(projViewModel.class);
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

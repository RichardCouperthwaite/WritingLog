package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;
import java.util.Date;

import edu.tamu.richardcouperthwaite.writinglog.databinding.ActivityMainBinding;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    long time = System.currentTimeMillis();
    Project freewriteproject = new Project(0, "Just Write Session", 0, "No Comments from last session.", "", time+"");
    Context appcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        appcontext = this;

        binding.btnFreeWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSession(view);
            }
        });

        binding.btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewProject(view);
            }
        });

        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewProjectList(view);
            }
        });

        binding.btnRecords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewStatistics(view);
            }
        });


    }

    public void goToSession(View view) {
        Intent intent = new Intent(this, WritingSession.class);
        intent.putExtra("PROJECT", freewriteproject);
        startActivity(intent);
    }

    public void createNewProject(View view) {
        Intent intent = new Intent(this, newProject.class);
        startActivity(intent);
    }

    public void viewProjectList(View view) {
        Intent intent = new Intent(this, ProjectList.class);
        startActivity(intent);
    }

    public void viewStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }

}

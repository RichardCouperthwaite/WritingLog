package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.tamu.richardcouperthwaite.writinglog.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSession(View view) {
        Intent intent = new Intent(this, WritingSession.class);
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
        Intent intent = new Intent(this, Statistics.class);
        startActivity(intent);
    }

    public void viewSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}

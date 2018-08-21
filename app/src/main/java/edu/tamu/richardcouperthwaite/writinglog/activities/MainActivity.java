package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;

public class MainActivity extends AppCompatActivity {
    Project freewriteproject = new Project(0, "Just Write Session", 0, "No Comments from last session.", "", "");
    Context appcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        appcontext = this;
    }

    @OnClick(R.id.btnFreeWrite)
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

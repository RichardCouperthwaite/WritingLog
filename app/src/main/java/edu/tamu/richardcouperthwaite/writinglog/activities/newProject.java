package edu.tamu.richardcouperthwaite.writinglog.activities;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.projViewModel;

public class newProject extends AppCompatActivity {
    @BindView(R.id.etProjName)
    EditText ProjectName;
    @BindView(R.id.etProjDesc)
    EditText ProjectDescription;
    @BindView(R.id.dtDueDate)
    EditText DueDate;
    private projViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(projViewModel.class);
    }

    @OnClick(R.id.btnCreateReturn)
    public void ProjectCreateReturn(View view) {
        int id;
        Calendar calendar = GregorianCalendar.getInstance();
        id = (int) calendar.getTimeInMillis();
        Project project = new Project(id, ProjectName.getText().toString(), 0, ProjectDescription.getText().toString(), "", DueDate.getText().toString());
        int check = checkProjectValid(project);

        if (check == 1) {
            viewModel.insert(project);
            Toast.makeText(view.getContext(), "New Project Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            Context context = view.getContext();
            ContextCompat.startActivity(context, intent, Bundle.EMPTY);
        } else {
            Toast.makeText(view.getContext(), "Please complete all fields and try again (Date must be correct format)", Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.btnCreateStart)
    public void ProjectCreateStart(View view) {
        int id;
        Calendar calendar = GregorianCalendar.getInstance();
        id = (int) calendar.getTimeInMillis();
        Project project = new Project(id, ProjectName.getText().toString(), 0, ProjectDescription.getText().toString(), "", DueDate.getText().toString());
        int check = checkProjectValid(project);

        if (check == 1) {
            viewModel.insert(project);
            Toast.makeText(view.getContext(), "New Project Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), WritingSession.class);
            intent.putExtra("PROJECT", project);
            Context context = view.getContext();
            ContextCompat.startActivity(context, intent, Bundle.EMPTY);
        } else {
            Toast.makeText(view.getContext(), "Please complete all fields and try again (Date must be correct format)", Toast.LENGTH_LONG).show();
        }
    }

    private int checkProjectValid(Project project) {
        int valid = 1;

        if (project.getName().equals("")) {
            valid = 2;
        }
        if (project.getDescription().equals("")) {
            valid = 3;
        }
        String [] date = project.getDueDate().split("/");
        try {
            int month = Integer.parseInt(date[0]);
            int day = Integer.parseInt(date[1]);
            if (month>12) {
                valid = 4;
            }
            if (day > 31) {
                valid = 5;
            }
        } catch (Exception e) {
            valid = 6;
        }

        return valid;
    }
}

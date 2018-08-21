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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.projViewModel;

public class editProjectDetails extends AppCompatActivity {
    @BindView(R.id.etEditProjName)
    EditText EditProjectName;
    @BindView(R.id.etEditProjDesc)
    EditText EditProjectDescription;
    @BindView(R.id.etEditDueDate)
    EditText EditProjectDate;

    Project project;
    private projViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project_details);
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        viewModel = ViewModelProviders.of(this).get(projViewModel.class);
        if (extras != null) {
            project = (Project) extras.getSerializable("PROJECT");
            if (project != null) {
                EditProjectName.setText(project.getName());
                EditProjectDescription.setText(project.getDescription());
                EditProjectDate.setText(project.getDueDate());
            }
        }
    }

    @OnClick(R.id.btnEditProject)
    public void editProject(View view) {
        Project edited;
        edited = new Project(project.getID(), EditProjectName.getText().toString(), project.getTime(), EditProjectDescription.getText().toString(), project.getLastComment(), EditProjectDate.getText().toString());
        viewModel.update(edited);
        Toast.makeText(view.getContext(), "Project Updated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), ProjectList.class);
        Context context = view.getContext();
        ContextCompat.startActivity(context, intent, Bundle.EMPTY);
    }
}

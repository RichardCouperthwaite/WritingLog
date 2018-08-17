package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCreateReturn)
    public void ProjectCreateReturn(View view) {
        projViewModel viewModel = new projViewModel(getApplication());
        List<Project> mprojList = viewModel.getProjectList().getValue();
        int Index = 0;
        for (int i=1; i<=mprojList.size(); i++) {
            if (mprojList.get(i).getID()>Index) {
                Index = mprojList.get(i).getID();
            }
        }
        Project project = new Project(Index+1, ProjectName.getText().toString(), 0, ProjectDescription.getText().toString(), "", DueDate.getText().toString());
        viewModel.insert(project);
    }
}

package edu.tamu.richardcouperthwaite.writinglog.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.projViewModel;
import edu.tamu.richardcouperthwaite.writinglog.databinding.ActivityEditProjectDetailsBinding;

public class editProjectDetails extends AppCompatActivity {
    private ActivityEditProjectDetailsBinding binding;
    Project project;
    private projViewModel viewModel;
    long dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProjectDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        viewModel = new ViewModelProvider(this).get(projViewModel.class);
        if (extras != null) {
            project = (Project) extras.getSerializable("PROJECT");
            if (project != null) {
                binding.etEditProjName.setText(project.getName());
                binding.etEditProjDesc.setText(project.getDescription());
                long date = Long.parseLong(project.getDueDate());
                binding.dtCalendar2.setDate(date);
            }
        }

        binding.btnEditProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editProject(view);
            }
        });

        binding.dtCalendar2.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                dueDate = c.getTimeInMillis(); //this is what you want to use later
            }
        });
    }

    public void editProject(View view) {
        Project edited;
        long date = binding.dtCalendar2.getDate();
        edited = new Project(project.getID(), binding.etEditProjName.getText().toString(),
                project.getTime(), binding.etEditProjDesc.getText().toString(),
                project.getLastComment(), dueDate+"");
        viewModel.update(edited);
        Toast.makeText(view.getContext(), "Project Updated", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), ProjectList.class);
        Context context = view.getContext();
        startActivity(intent);
    }
}

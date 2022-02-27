package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.projViewModel;
import edu.tamu.richardcouperthwaite.writinglog.databinding.ActivityNewProjectBinding;

public class newProject extends AppCompatActivity {
    private ActivityNewProjectBinding binding;
    private projViewModel viewModel;
    long dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewProjectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(projViewModel.class);

        binding.btnCreateReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectCreateReturn(view);
            }
        });

        binding.btnCreateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProjectCreateStart(view);
            }
        });

        binding.dtCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);
                dueDate = c.getTimeInMillis(); //this is what you want to use later
            }
        });
    }

    public void ProjectCreateReturn(View view) {
        int id;
        Calendar calendar = GregorianCalendar.getInstance();
        id = (int) calendar.getTimeInMillis();
        long date = binding.dtCalendar.getDate();
        Project project = new Project(id, binding.etProjName.getText().toString(), 0,
                binding.etProjDesc.getText().toString(), "", dueDate+"");
        int check = checkProjectValid(project);

        if (check == 1) {
            viewModel.insert(project);
            Toast.makeText(view.getContext(), "New Project Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(view.getContext(), "Please complete all fields and try again (Date must be correct format)", Toast.LENGTH_LONG).show();
        }
    }

    public void ProjectCreateStart(View view) {
        int id;
        Calendar calendar = GregorianCalendar.getInstance();
        id = (int) calendar.getTimeInMillis();
        long date = binding.dtCalendar.getDate();
        Project project = new Project(id, binding.etProjName.getText().toString(), 0,
                binding.etProjDesc.getText().toString(), "", dueDate+"");
        int check = checkProjectValid(project);

        if (check == 1) {
            viewModel.insert(project);
            Toast.makeText(view.getContext(), "New Project Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(view.getContext(), WritingSession.class);
            intent.putExtra("PROJECT", project);
            startActivity(intent);
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

        return valid;
    }
}

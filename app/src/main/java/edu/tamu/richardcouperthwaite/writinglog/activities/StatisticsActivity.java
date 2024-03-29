package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import edu.tamu.richardcouperthwaite.writinglog.models.Session;
import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;
import edu.tamu.richardcouperthwaite.writinglog.models.sessionViewModel;
import edu.tamu.richardcouperthwaite.writinglog.databinding.ActivityStatisticsBinding;
import edu.tamu.richardcouperthwaite.writinglog.models.statViewModel;

import java.util.List;
import java.util.Locale;

public class StatisticsActivity extends AppCompatActivity {
    private ActivityStatisticsBinding binding;
    private sessionViewModel msessViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        statViewModel mstatViewModel;
        super.onCreate(savedInstanceState);
        binding = ActivityStatisticsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mstatViewModel = new ViewModelProvider(this).get(statViewModel.class);
        mstatViewModel.getStatList().observe(this, new Observer<List<Statistics>>() {
            @Override
            public void onChanged(@Nullable List<Statistics> statistics) {
                displayData(statistics);
            }
        });

        msessViewModel = new ViewModelProvider(this).get(sessionViewModel.class);

        binding.btnExportData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveLog();
            }
        });


    }

    private void displayData(List<Statistics> statistics) {
        String currWT = "0";
        String currWD = "0,0,0,0,0,0,0";
        String prevWT = "0";
        String prevWD = "0";
        String currMT = "0";
        String currMD = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
        String prevMT = "0";
        String prevMD = "0";
        String currDIM = "0";
        String prevDIM = "0";
        int currentWeekDays = 0;
        int currentMonthDays = 0;

        try {
            for (int i = 0; i < statistics.size(); i++) {
                switch (statistics.get(i).getTitle()) {
                    case "CurrentWeekTime":
                        currWT = statistics.get(i).getValue();
                        break;
                    case "CurrentWeekDays":
                        currWD = statistics.get(i).getValue();
                        break;
                    case "PreviousWeekTime":
                        prevWT = statistics.get(i).getValue();
                        break;
                    case "PreviousWeekDays":
                        prevWD = statistics.get(i).getValue();
                        break;
                    case "CurrentMonthTime":
                        currMT = statistics.get(i).getValue();
                        break;
                    case "CurrentMonthDays":
                        currMD = statistics.get(i).getValue();
                        break;
                    case "PreviousMonthTime":
                        prevMT = statistics.get(i).getValue();
                        break;
                    case "PreviousMonthDays":
                        prevMD = statistics.get(i).getValue();
                        break;
                    case "CurrentDIM":
                        currDIM = statistics.get(i).getValue();
                        break;
                    case "PreviousDIM":
                        prevDIM = statistics.get(i).getValue();
                        break;
                }
            }
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "No statistics found!", Toast.LENGTH_SHORT).show();
        }

        String [] list1 = currMD.split(",");
        if (list1.length >= 31) {
            for (int i = 0; i < 31; i++) {
                if (list1[i].equals("1")) {
                    currentMonthDays++;
                }
            }
        }

        String [] list2 = currWD.split(",");
        if (list2.length >= 7) {
            for (int i = 0; i < 7; i++) {
                if (list2[i].equals("1")) {
                    currentWeekDays++;
                }
            }
        }

        binding.tvcurrenttimeweek.setText(timeFormat(currWT));
        binding.tvcurrentdaysweek.setText(String.format(Locale.US, "%d/7", currentWeekDays));
        binding.tvprevtimeweek.setText(timeFormat(prevWT));
        binding.tvprevdaysweek.setText(String.format(Locale.US, "%s/7", prevWD));
        binding.tvcurrenttimemonth.setText(timeFormat(currMT));
        binding.tvcurrentdaysmonth.setText(String.format(Locale.US, "%d/%s", currentMonthDays, currDIM));
        binding.tvprevtimemonth.setText(timeFormat(prevMT));
        binding.tvprevdaysmonth.setText(String.format(Locale.US, "%s/%s", prevMD, prevDIM));
    }

    public String timeFormat(String time) {
        String format = "%s:%s:%s";
        int timeint = Integer.parseInt(time);
        int hours = timeint/60;
        int minutes = timeint-hours*60;
        int days = hours/24;
        hours = hours-days*24;
        String min;
        String hrs;
        String dys;
        if (minutes<10) { min = "0" + minutes; } else { min = "" + minutes; }
        if (hours<10) { hrs = "0" + hours; } else { hrs = "" + hours; }
        if (days<10) { dys = "0" + days; } else { dys = "" + days; }
        return String.format(format, dys, hrs, min);
    }

    public void saveLog() {

        msessViewModel.getSessionList().observe(this, new Observer<List<Session>>() {
            @Override
            public void onChanged(@Nullable List<Session> sessions) {
                StringBuilder logData = new StringBuilder("WritingLog.csv \nDate, Start Time, End Time, Total Time, Project Name, End of Session Comments \n");
                if (sessions!=null) {
                    for (int i = 0; i < sessions.size(); i++) {
                        String logEntry = String.format("%s, %s, %s, %s, \"%s\",  \"%s\" \n", sessions.get(i).getDate(), sessions.get(i).getStart(), sessions.get(i).getEnd(), sessions.get(i).getTotal(), sessions.get(i).getProject(), sessions.get(i).getComments());
                        logData.append(logEntry);
                    }
                    try {
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, logData.toString());
                        startActivity(Intent.createChooser(sharingIntent, "Share Log Data"));

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Could not export data at this time!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No Data in Writing Log!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}

package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.app.DownloadManager;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Session;
import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;
import edu.tamu.richardcouperthwaite.writinglog.models.sessionViewModel;
import edu.tamu.richardcouperthwaite.writinglog.models.statViewModel;


public class StatisticsActivity extends AppCompatActivity {
    @BindView(R.id.tvcurrentdaysmonth)
    TextView tvcurrentdaysmonth;
    @BindView(R.id.tvcurrentdaysweek)
    TextView tvcurrentdaysweek;
    @BindView(R.id.tvcurrenttimemonth)
    TextView tvcurrenttimemonth;
    @BindView(R.id.tvcurrenttimeweek)
    TextView tvcurrenttimeweek;
    @BindView(R.id.tvprevdaysmonth)
    TextView tvprevdaysmonth;
    @BindView(R.id.tvprevdaysweek)
    TextView tvprevdaysweek;
    @BindView(R.id.tvprevtimemonth)
    TextView tvprevtimemonth;
    @BindView(R.id.tvprevtimeweek)
    TextView tvprevtimeweek;

    private statViewModel mstatViewModel;
    private sessionViewModel msessViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);

        mstatViewModel = ViewModelProviders.of(this).get(statViewModel.class);
        mstatViewModel.getStatList().observe(this, new Observer<List<Statistics>>() {
            @Override
            public void onChanged(@Nullable List<Statistics> statistics) {
                displayData(statistics);
            }
        });

        msessViewModel = ViewModelProviders.of(this).get(sessionViewModel.class);


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

        tvcurrenttimeweek.setText(timeFormat(currWT));
        tvcurrentdaysweek.setText(String.format("%d/7", currentWeekDays));
        tvprevtimeweek.setText(timeFormat(prevWT));
        tvprevdaysweek.setText(prevWD + "/7");
        tvcurrenttimemonth.setText(timeFormat(currMT));
        tvcurrentdaysmonth.setText(String.format("%d/%s", currentMonthDays, currDIM));
        tvprevtimemonth.setText(timeFormat(prevMT));
        tvprevdaysmonth.setText(prevMD + "/" + prevDIM);
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

    @OnClick(R.id.btnExportData)
    public void saveLog() {

        msessViewModel.getSessionList().observe(this, new Observer<List<Session>>() {
            @Override
            public void onChanged(@Nullable List<Session> sessions) {
                if (isExternalStorageWritable()) {
                    String logData = "WritingLog.csv \nDate, Start Time, End Time, Total Time, Project Name, End of Session Comments \n";
                    try {
                        for (int i = 0; i < sessions.size(); i++) {
                            String logEntry = String.format("%s, %s, %s, %s, \"%s\",  \"%s\" \n", sessions.get(i).getDate(), sessions.get(i).getStart(), sessions.get(i).getEnd(), sessions.get(i).getTotal(), sessions.get(i).getProject(), sessions.get(i).getComments());
                            logData += logEntry;
                        }

                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, logData);
                        startActivity(Intent.createChooser(sharingIntent, "Share Log Data"));

                        //Toast.makeText(getApplicationContext(), "Writing Log Exported", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "No Data in Writing Log: "+ e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Could not save data at this time", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public File getLogFile(String albumName) throws IOException {
        // Get the directory for the user's public pictures directory.
        File file = new File(getApplicationContext().getFilesDir(), albumName);
        //if (!file.mkdirs()) {
        //    throw new IOException("Could not create directory " + file.toString());
        //}
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (Exception e) {
            throw new IOException("Failed to create file "+ e.getMessage());
        }
        return file;
    }
}

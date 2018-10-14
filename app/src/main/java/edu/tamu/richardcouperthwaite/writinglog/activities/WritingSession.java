package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.Session;
import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;
import edu.tamu.richardcouperthwaite.writinglog.models.projViewModel;
import edu.tamu.richardcouperthwaite.writinglog.models.sessionViewModel;
import edu.tamu.richardcouperthwaite.writinglog.models.statViewModel;


public class WritingSession extends AppCompatActivity {
    String starttime;
    String endtime;
    String date;
    int hourstart;
    int minutestart;
    int hourend;
    int minuteend;
    int totaltime;
    Project project;
    String commentInput = "This is the predefined text";
    Boolean SessStart = false;
    projViewModel viewModel;
    statViewModel statViewModel;
    sessionViewModel sessionViewModel;
    @BindView(R.id.projName)
    TextView projName;
    @BindView(R.id.sessLastComment)
    TextView comment;
    @BindView(R.id.projTime)
    TextView projTime;
    @BindView(R.id.sessTimer)
    Chronometer timer;

    String currWT;
    String currMT;
    String weekstart;
    String monthstart;
    String currWD;
    String currMD;
    String prevWT;
    String prevWD;
    String prevMT;
    String prevMD;
    String currDIM;
    String prevDIM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_session);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(projViewModel.class);
        statViewModel = ViewModelProviders.of(this).get(statViewModel.class);
        sessionViewModel = ViewModelProviders.of(this).get(sessionViewModel.class);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            project = (Project) extras.getSerializable("PROJECT");
            if (project!=null) {
                int hours = project.getTime() / 60;
                int minutes = project.getTime() - 60 * hours;
                String hoursstring;
                String minutesstring;
                if (hours < 10) {
                    hoursstring = "0" + hours;
                } else {
                    hoursstring = "" + hours;
                }
                if (minutes < 10) {
                    minutesstring = "0" + minutes;
                } else {
                    minutesstring = "" + minutes;
                }
                String timeSpent = String.format("%s:%s", hoursstring, minutesstring);
                projName.setText(project.getName());
                comment.setText(project.getLastComment());
                projTime.setText(timeSpent);
            }
        }

        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                timer = chronometer;
            }
        });

        statViewModel.getStatList().observe(this, new Observer<List<Statistics>>() {
            @Override
            public void onChanged(@Nullable List<Statistics> statistics) {
                updateStats(statistics);
            }
        });
    }

    @OnClick(R.id.startSess)
    public void startSession() {
        if (SessStart == false) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
            date = dateFormat.format(calendar.getTime());
            starttime = timeFormat.format(calendar.getTime());
            String timearray[] = starttime.split(":");
            hourstart = Integer.parseInt(timearray[0]);
            minutestart = Integer.parseInt(timearray[1]);
            timer.setTextColor(Color.parseColor("#5B6236"));
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
            SessStart = true;
        }
    }

    @OnClick(R.id.endSess)
    public void endSession() {
        if (SessStart) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.US);
            endtime = timeFormat.format(calendar.getTime());
            String timearray[] = endtime.split(":");
            hourend = Integer.parseInt(timearray[0]);
            minuteend = Integer.parseInt(timearray[1]);
            totaltime = ((hourend-hourstart)*60 + minuteend-minutestart);
            timer.stop();
            SessStart = false;
            getComment(this);
        }
    }

    private void getComment(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(WritingSession.this, R.style.DialogTheme);
        builder.setTitle("Enter comments for next session:");
        builder.setCancelable(false);

        // Set up the input
        final EditText input = new EditText(WritingSession.this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setHint("Comments...");
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                commentInput = input.getText().toString();
                project.setLastComment(commentInput);
                project.setTime(project.getTime()+totaltime);
                saveData(project);
                saveStats();
                int sessid;
                Calendar calendar = GregorianCalendar.getInstance();
                sessid = (int) calendar.getTimeInMillis();
                sessionViewModel.insert(new Session(sessid, date, starttime, endtime, ""+totaltime, project.getName(), commentInput));
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        builder.show();
    }

    private void saveData(Project proj) {
        viewModel.update(proj);
    }

    private void updateStats(List<Statistics> statistics) {
        if (statistics!=null) {
            for (int i = 0; i < statistics.size(); i++) {
                switch (statistics.get(i).getTitle()) {
                    case "CurrentWeekTime":
                        currWT = statistics.get(i).getValue();
                        break;
                    case "CurrentWeekDays":
                        currWD = statistics.get(i).getValue();
                        break;
                    case "CurrentMonthTime":
                        currMT = statistics.get(i).getValue();
                        break;
                    case "CurrentMonthDays":
                        currMD = statistics.get(i).getValue();
                        break;
                    case "WeekStart":
                        weekstart = statistics.get(i).getValue();
                        break;
                    case "MonthStart":
                        monthstart = statistics.get(i).getValue();
                        break;
                    case "PreviousWeekTime":
                        prevWT = statistics.get(i).getValue();
                        break;
                    case "PreviousWeekDays":
                        prevWD = statistics.get(i).getValue();
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
        }
    }

    private void saveStats() {
        Calendar nowdate1 = GregorianCalendar.getInstance();
        Calendar nowdate2 = GregorianCalendar.getInstance();
        Calendar weekdate = GregorianCalendar.getInstance();
        Calendar monthdate = GregorianCalendar.getInstance();
        String[] weekdays;
        try {
            weekdays = currWD.split(",");
        } catch (NullPointerException e) {
            currWT = "0";
            currMT = "0";
            weekstart = "01/01/1990";
            monthstart = "01/01/1990";
            currWD = "0,0,0,0,0,0,0";
            currMD = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
            prevWT = "0";
            prevWD = "0";
            prevMT = "0";
            prevMD = "0";
            currDIM = "0";
            prevDIM = "0";
            statViewModel.insert(new Statistics("CurrentWeekTime", currWT));
            statViewModel.insert(new Statistics("CurrentWeekDays", currWD));
            statViewModel.insert(new Statistics("CurrentMonthTime", currMT));
            statViewModel.insert(new Statistics("CurrentMonthDays", currMD));
            statViewModel.insert(new Statistics("WeekStart", weekstart));
            statViewModel.insert(new Statistics("MonthStart", monthstart));
            statViewModel.insert(new Statistics("PreviousWeekTime", prevWT));
            statViewModel.insert(new Statistics("PreviousWeekDays", prevWD));
            statViewModel.insert(new Statistics("PreviousMonthTime", prevMT));
            statViewModel.insert(new Statistics("PreviousMonthDays", prevMD));
            statViewModel.insert(new Statistics("CurrentDIM", currDIM));
            statViewModel.insert(new Statistics("PreviousDIM", prevDIM));
            weekdays = currWD.split(",");
        }
        String [] monthdays = currMD.split(",");
        String [] dateSplit = date.split("/");
        String [] weekSplit = weekstart.split("/");
        String [] monthSplit = monthstart.split("/");
        nowdate1.set(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[0])-1, Integer.parseInt(dateSplit[1]));
        nowdate2.set(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[0])-1, Integer.parseInt(dateSplit[1]));
        weekdate.set(Integer.parseInt(weekSplit[2]), Integer.parseInt(weekSplit[0])-1, Integer.parseInt(weekSplit[1]));
        monthdate.set(Integer.parseInt(monthSplit[2]), Integer.parseInt(monthSplit[0])-1, Integer.parseInt(monthSplit[1]));
        int dayofWeek = nowdate1.get(Calendar.DAY_OF_WEEK) -1;
        int dayofmonth = nowdate1.get(Calendar.DATE) -1;
        nowdate1.add(Calendar.DATE, (-1)*dayofWeek);
        nowdate2.add(Calendar.DATE, (-1)*dayofmonth);
        //update values for week stats
        if (nowdate1.compareTo(weekdate) > 7) {
            int count = 0;
            for (int i=0; i<7; i++) {
                if (weekdays[i].equals("1")) {
                    count++;
                    weekdays[i] = "0";
                }
            }
            prevWD = "" + count;
            prevWT = currWT;
            currWT = "0";
            weekstart = String.format(Locale.US, "%d/%d/%d", nowdate1.get(Calendar.MONTH), nowdate1.get(Calendar.DATE), nowdate1.get(Calendar.YEAR));
        }
        weekdays[dayofWeek] = "1";
        StringBuilder newWDString = new StringBuilder("");
        for (int i=0; i<7; i++) {
            newWDString.append(weekdays[i]);
            newWDString.append(",");
        }
        currWD = newWDString.toString();
        int presentWeekTime = Integer.parseInt(currWT);
        presentWeekTime += totaltime;
        currWT = "" + presentWeekTime;
        //update values for month stats
        if (nowdate2.get(Calendar.MONTH) < monthdate.get(Calendar.MONTH)) {
            int count = 0;
            for (int i=0; i<31; i++) {
                if (monthdays[i].equals("1")) {
                    count++;
                    monthdays[i] = "0";
                }
            }
            prevMD = "" + count;
            prevMT = currMT;
            currMT = "0";
            prevDIM = currDIM;
            monthstart = String.format(Locale.US, "%d/%d/%d", nowdate2.get(Calendar.MONTH), nowdate2.get(Calendar.DATE), nowdate2.get(Calendar.YEAR));
        }
        monthdays[dayofmonth] = "1";
        StringBuilder newMDString = new StringBuilder("");
        for (int i=0; i<31; i++) {
            newMDString.append(monthdays[i]);
            newMDString.append(",");
        }
        currMD = newMDString.toString();
        int presentMonthTime = Integer.parseInt(currMT);
        presentMonthTime += totaltime;
        currMT = "" + presentMonthTime;
        currDIM = "" + nowdate2.getActualMaximum(Calendar.DAY_OF_MONTH);

        statViewModel.update(new Statistics("CurrentWeekTime", currWT));
        statViewModel.update(new Statistics("CurrentWeekDays", currWD));
        statViewModel.update(new Statistics("CurrentMonthTime", currMT));
        statViewModel.update(new Statistics("CurrentMonthDays", currMD));
        statViewModel.update(new Statistics("WeekStart", weekstart));
        statViewModel.update(new Statistics("MonthStart", monthstart));
        statViewModel.update(new Statistics("PreviousWeekTime", prevWT));
        statViewModel.update(new Statistics("PreviousWeekDays", prevWD));
        statViewModel.update(new Statistics("PreviousMonthTime", prevMT));
        statViewModel.update(new Statistics("PreviousMonthDays", prevMD));
        statViewModel.update(new Statistics("CurrentDIM", currDIM));
        statViewModel.update(new Statistics("PreviousDIM", prevDIM));
    }
}














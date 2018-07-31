package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;

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
    @BindView(R.id.projName)
    TextView projName;
    @BindView(R.id.sessLastComment)
    TextView comment;
    @BindView(R.id.projTime)
    TextView projTime;
    @BindView(R.id.sessTimer)
    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_session);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            project = (Project) extras.getSerializable("PROJECT");
            int hours = (int) project.getTimespent()/60;
            int minutes = project.getTimespent()-60*hours;
            String timeSpent = String.format("%d:%d", hours, minutes);
            projName.setText(project.getTitle());
            comment.setText(project.getLastcomment());
            projTime.setText(timeSpent);
        }

        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                timer = chronometer;
            }
        });

    }



    @OnClick(R.id.startSess)
    public void startSession() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        date = dateFormat.format(calendar.getTime());
        starttime = timeFormat.format(calendar.getTime());
        String timearray[] = starttime.split(":");
        hourstart = Integer.parseInt(timearray[0]);
        minutestart = Integer.parseInt(timearray[1]);
        timer.setTextColor(Color.parseColor("#5B6236"));
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }

    @OnClick(R.id.endSess)
    public void endSession() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        endtime = timeFormat.format(calendar.getTime());
        String timearray[] = endtime.split(":");
        hourend = Integer.parseInt(timearray[0]);
        minuteend = Integer.parseInt(timearray[1]);
        totaltime = ((hourend-hourstart)*60 + minuteend-minutestart);
        timer.stop();
    }
}

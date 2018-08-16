package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Chronometer;
import android.widget.EditText;
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
    String commentInput = "This is the predefined text";
    Boolean SessStart = false;
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
            int hours = (int) project.getTime()/60;
            int minutes = project.getTime()-60*hours;
            String timeSpent = String.format("%d:%d", hours, minutes);
            projName.setText(project.getName());
            comment.setText(project.getLastComment());
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
        SessStart = true;
    }

    @OnClick(R.id.endSess)
    public void endSession() {
        if (SessStart) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
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
                comment.setText(commentInput);

                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        builder.show();
    }

    private void saveData() {

    }
}

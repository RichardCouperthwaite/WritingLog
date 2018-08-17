package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;
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
    List<Statistics> mStatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);

        mstatViewModel = ViewModelProviders.of(this).get(statViewModel.class);
        mStatList = mstatViewModel.getStatList().getValue();
        if (mStatList != null) {
            if (mStatList.size() != 0) {
                displayData();
            } else {
                Toast.makeText(this, "No Statistics Found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error Loading Statistics", Toast.LENGTH_SHORT).show();
        }

    }

    private void displayData() {
        String currWT = "0";
        String currWD = "0";
        String prevWT = "0";
        String prevWD = "0";
        String currMT = "0";
        String currMD = "0";
        String prevMT = "0";
        String prevMD = "0";
        String currDIM = "0";
        String prevDIM = "0";
        int currentWeekDays = 0;
        int currentMonthDays = 0;

        for (int i = 0; i<mStatList.size(); i++) {
            switch(mStatList.get(i).getTitle()) {
                case "CurrentWeekTime":     currWT=mStatList.get(i).getValue();
                                            break;
                case "CurrentWeekDays":     currWD=mStatList.get(i).getValue();
                                            break;
                case "PreviousWeekTime":    prevWT=mStatList.get(i).getValue();
                                            break;
                case "PreviousWeekDays":    prevWT=mStatList.get(i).getValue();
                                            break;
                case "CurrentMonthTime":    currMT=mStatList.get(i).getValue();
                                            break;
                case "CurrentMonthDays":    currMD=mStatList.get(i).getValue();
                                            break;
                case "PreviousMonthTime":   prevMT=mStatList.get(i).getValue();
                                            break;
                case "PreviousMonthDays":   prevMD=mStatList.get(i).getValue();
                                            break;
                case "CurrentDIM":  currDIM=mStatList.get(i).getValue();
                                    break;
                case "PreviousDIM": prevDIM=mStatList.get(i).getValue();
                                    break;
            }
        }
        String [] list1 = currMD.split(",");
        for (String day : list1) {
            if (day.equals("1")) {
                currentMonthDays++;
            }
        }

        String [] list2 = currWD.split(",");
        for (String day : list2) {
            if (day.equals("1")) {
                currentWeekDays++;
            }
        }

        tvcurrenttimeweek.setText(currWT);
        tvcurrentdaysweek.setText(String.format("%d/7", currentWeekDays));
        tvprevtimeweek.setText(prevWT);
        tvprevdaysweek.setText(prevWD + "/7");
        tvcurrenttimemonth.setText(currMT);
        tvcurrentdaysmonth.setText(String.format("%d/%s", currentMonthDays, currDIM));
        tvprevtimemonth.setText(prevMT);
        tvprevdaysmonth.setText(prevMD + "/" + prevDIM);
    }


}

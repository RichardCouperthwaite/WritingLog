package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
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
        mstatViewModel.getStatList().observe(this, new Observer<List<Statistics>>() {
            @Override
            public void onChanged(@Nullable List<Statistics> statistics) {
                displayData(statistics);
            }
        });
        
        
        //if (mStatList != null) {
        //    if (mStatList.size() != 0) {
        //        displayData();
        //    } else {
        //        Toast.makeText(this, "No Statistics Found", Toast.LENGTH_SHORT).show();
        //    }
        //} else {
        //    Toast.makeText(this, "Error Loading Statistics", Toast.LENGTH_SHORT).show();
        //}

    }

    private void displayData(List<Statistics> statistics) {
        String currWT = "a";
        String currWD = "a";
        String prevWT = "a";
        String prevWD = "a";
        String currMT = "a";
        String currMD = "a";
        String prevMT = "a";
        String prevMD = "a";
        String currDIM = "a";
        String prevDIM = "a";
        int currentWeekDays = 0;
        int currentMonthDays = 0;

        for (int i = 0; i<statistics.size(); i++) {
            switch(statistics.get(i).getTitle()) {
                case "CurrentWeekTime":     currWT=statistics.get(i).getValue();
                                            break;
                case "CurrentWeekDays":     currWD=statistics.get(i).getValue();
                                            break;
                case "PreviousWeekTime":    prevWT=statistics.get(i).getValue();
                                            break;
                case "PreviousWeekDays":    prevWD=statistics.get(i).getValue();
                                            break;
                case "CurrentMonthTime":    currMT=statistics.get(i).getValue();
                                            break;
                case "CurrentMonthDays":    currMD=statistics.get(i).getValue();
                                            break;
                case "PreviousMonthTime":   prevMT=statistics.get(i).getValue();
                                            break;
                case "PreviousMonthDays":   prevMD=statistics.get(i).getValue();
                                            break;
                case "CurrentDIM":  currDIM=statistics.get(i).getValue();
                                    break;
                case "PreviousDIM": prevDIM=statistics.get(i).getValue();
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

package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tamu.richardcouperthwaite.writinglog.R;


public class StatisticsActivity extends AppCompatActivity {
    ArrayList<String> statslist;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        ButterKnife.bind(this);

        //statslist = getStatisticDetails(getBaseContext());

        //displayData();
    }

    private void displayData() {
        tvcurrenttimeweek.setText(statslist.get(0));
        tvcurrentdaysweek.setText(statslist.get(1));
        tvprevtimeweek.setText(statslist.get(2));
        tvprevdaysweek.setText(statslist.get(3));
        tvcurrenttimemonth.setText(statslist.get(4));
        tvcurrentdaysmonth.setText(statslist.get(5));
        tvprevtimemonth.setText(statslist.get(6));
        tvprevdaysmonth.setText(statslist.get(7));
    }


}

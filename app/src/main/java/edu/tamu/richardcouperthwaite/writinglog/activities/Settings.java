package edu.tamu.richardcouperthwaite.writinglog.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tamu.richardcouperthwaite.writinglog.R;

import static edu.tamu.richardcouperthwaite.writinglog.models.FileIO.getStatisticDetails;

public class Settings extends AppCompatActivity {
    ArrayList<String> statistics;
    @BindView(R.id.tvcurrentdaysmonth)
    TextView tvcdmonth;
    @BindView(R.id.tvcurrentdaysweek)
    TextView tvcdweek;
    @BindView(R.id.tvcurrenttimemonth)
    TextView tvctmonth;
    @BindView(R.id.tvcurrenttimeweek)
    TextView tvctweek;
    @BindView(R.id.tvprevdaysmonth)
    TextView tvpdmonth;
    @BindView(R.id.tvprevdaysweek)
    TextView tvpdweek;
    @BindView(R.id.tvprevtimemonth)
    TextView tvptmonth;
    @BindView(R.id.tvprevtimeweek)
    TextView tvptweek;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Context context = this;
        ButterKnife.bind(this);

        statistics = getStatisticDetails(context);

        updateTextViewFields();
    }

    private void updateTextViewFields() {
        tvctweek.setText(statistics.get(0));
        tvcdweek.setText(statistics.get(1));
        tvptweek.setText(statistics.get(2));
        tvpdweek.setText(statistics.get(3));
        tvctmonth.setText(statistics.get(4));
        tvcdmonth.setText(statistics.get(5));
        tvptmonth.setText(statistics.get(6));
        tvpdmonth.setText(statistics.get(7));
    }
}

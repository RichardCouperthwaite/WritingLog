package edu.tamu.richardcouperthwaite.writinglog;

import android.os.Bundle;
import android.app.Activity;

public class WritingSession extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_session);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }
}

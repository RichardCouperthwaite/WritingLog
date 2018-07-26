package edu.tamu.richardcouperthwaite.writinglog.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tamu.richardcouperthwaite.writinglog.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class timer extends Fragment {


    public timer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

}

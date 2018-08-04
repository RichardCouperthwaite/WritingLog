package edu.tamu.richardcouperthwaite.writinglog.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.adapters.ProjectRecyclerViewAdapter;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;

import static edu.tamu.richardcouperthwaite.writinglog.models.FileIO.getProjectDetails;


/**
 * A simple {@link Fragment} subclass.
 */
public class projectlist_fragment extends Fragment {

    @BindView(R.id.rvprojectlist)
    RecyclerView rvMovies;
    private List<Project> projects;

    public projectlist_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_projectlist, container, false);
        ButterKnife.bind(this, view);

        initializeData(this.getContext());

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(llm);

        ProjectRecyclerViewAdapter adapter = new ProjectRecyclerViewAdapter(getContext(), projects);
        rvMovies.setAdapter(adapter);

        return view;
    }

    private void initializeData(Context context) {
        projects = getProjectDetails(context);
    }

}

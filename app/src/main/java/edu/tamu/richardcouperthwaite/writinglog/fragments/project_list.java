package edu.tamu.richardcouperthwaite.writinglog.fragments;


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
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.adapters.project_view_adapter;
import edu.tamu.richardcouperthwaite.writinglog.models.project;

/**
 * A simple {@link Fragment} subclass.
 */
public class project_list extends Fragment {

    @BindView(R.id.rvProjecList)
    RecyclerView rvProjectList;
    private List<project> projects;

    public project_list() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_project_list, container, false);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rvProjectList.setHasFixedSize(true);
        rvProjectList.setLayoutManager(llm);

        project_view_adapter adapter = new project_view_adapter(this.getContext(), projects);
        rvProjectList.setAdapter(adapter);

        return view;
    }

    private void initializeData() {
        // get information from file

        projects = new ArrayList<>();

        projects.add(new project("Test Project 1", "The first test project", 500, "8/10/2018", "This is the last comment", "these are the rest of the comments"));
        projects.add(new project("Test Project 2", "The second test project", 500, "8/10/2018", "This is the last comment", "these are the rest of the comments"));
        projects.add(new project("Test Project 3", "The third test project", 500, "8/10/2018", "This is the last comment", "these are the rest of the comments"));
        projects.add(new project("Test Project 4", "The fourth test project", 500, "8/10/2018", "This is the last comment", "these are the rest of the comments"));
        projects.add(new project("Test Project 5", "The fifth test project", 500, "8/10/2018", "This is the last comment", "these are the rest of the comments"));
        projects.add(new project("Test Project 6", "The sixth test project", 500, "8/10/2018", "This is the last comment", "these are the rest of the comments"));
        projects.add(new project("Test Project 7", "The seventh test project", 500, "8/10/2018", "This is the last comment", "these are the rest of the comments"));
        projects.add(new project("Test Project 8", "The eighth test project", 500, "8/10/2018", "This is the last comment", "these are the rest of the comments"));

    }


}

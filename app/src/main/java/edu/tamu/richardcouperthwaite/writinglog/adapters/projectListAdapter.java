package edu.tamu.richardcouperthwaite.writinglog.adapters;


import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.activities.WritingSession;
import edu.tamu.richardcouperthwaite.writinglog.activities.editProjectDetails;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;
import edu.tamu.richardcouperthwaite.writinglog.models.projViewModel;

public class projectListAdapter extends RecyclerView.Adapter<projectListAdapter.ProjectViewHolder> {

    class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleView;
        private final TextView description;
        private final TextView duedate;
        private final ImageButton startSession;
        private final ImageButton deleteProject;
        private final ImageButton editProject;


        private ProjectViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tvProjectTitle);
            description = itemView.findViewById(R.id.tvProjectOverview);
            startSession = itemView.findViewById(R.id.ibstart);
            deleteProject = itemView.findViewById(R.id.ibarchive);
            editProject = itemView.findViewById(R.id.ibedit);
            duedate = itemView.findViewById((R.id.tvDate));

            startSession.setOnClickListener(this);
            deleteProject.setOnClickListener(this);
            editProject.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == startSession.getId()) {
                Intent intent = new Intent(view.getContext(), WritingSession.class);
                intent.putExtra("PROJECT", mProjectList.get(getBindingAdapterPosition()));
                Context context = view.getContext();
                startActivity(context, intent, Bundle.EMPTY);
            } else if(view.getId() == deleteProject.getId()) {
                checkDelete(view.getContext(),getBindingAdapterPosition());
            } else if(view.getId() == editProject.getId()) {
                Intent intent = new Intent(view.getContext(), editProjectDetails.class);
                intent.putExtra("PROJECT", mProjectList.get(getBindingAdapterPosition()));
                Context context = view.getContext();
                startActivity(context, intent, Bundle.EMPTY);
            }

        }
    }

    private final LayoutInflater mInflater;
    private List<Project> mProjectList;
    private projViewModel projViewModel;

    public projectListAdapter(Context context) {mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.project_item, parent, false);
        return new ProjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        if (mProjectList != null) {
            Project current = mProjectList.get(position);
            holder.titleView.setText(current.getName());
            holder.description.setText(current.getDescription());
            long date = Long.parseLong(current.getDueDate());
            String strDate = new SimpleDateFormat("dd MMM yyyy").format(new Date(date));
            holder.duedate.setText(strDate);
        }
    }

    public void setProjectList(List<Project> projects, projViewModel mprojViewModel){
        mProjectList = projects;
        projViewModel = mprojViewModel;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mProjectList != null)
            return mProjectList.size();
        else return 0;
    }

    private void checkDelete(final Context context, final int Index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTheme);
        builder.setTitle("Are you sure you want to delete this project");

        // Set up the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context, "Project Deleted", Toast.LENGTH_SHORT).show();
                projViewModel.delete(mProjectList.get(Index));
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context, "Project Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();
    }
}

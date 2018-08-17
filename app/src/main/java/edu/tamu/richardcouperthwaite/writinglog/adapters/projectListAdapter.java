package edu.tamu.richardcouperthwaite.writinglog.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.activities.MainActivity;
import edu.tamu.richardcouperthwaite.writinglog.activities.ProjectList;
import edu.tamu.richardcouperthwaite.writinglog.activities.WritingSession;
import edu.tamu.richardcouperthwaite.writinglog.activities.editProjectDetails;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;

import static android.support.v4.content.ContextCompat.startActivities;
import static android.support.v4.content.ContextCompat.startActivity;

public class projectListAdapter extends RecyclerView.Adapter<projectListAdapter.ProjectViewHolder> {

    class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleView;
        private final TextView description;
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

            startSession.setOnClickListener(this);
            deleteProject.setOnClickListener(this);
            editProject.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == startSession.getId()) {
                Toast.makeText(view.getContext(), "Start Session = "+ mProjectList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), WritingSession.class);
                intent.putExtra("PROJECT", mProjectList.get(getAdapterPosition()));
                Context context = view.getContext();
                startActivity(context, intent, Bundle.EMPTY);
            } else if(view.getId() == deleteProject.getId()) {
                Toast.makeText(view.getContext(), "Delete Project = " + mProjectList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                checkDelete(view.getContext());
            } else if(view.getId() == editProject.getId()) {
                Toast.makeText(view.getContext(), "Edit Project = " + mProjectList.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), editProjectDetails.class);
                intent.putExtra("PROJECT", mProjectList.get(getAdapterPosition()));
                Context context = view.getContext();
                startActivity(context, intent, Bundle.EMPTY);
            }

        }
    }

    private final LayoutInflater mInflater;
    private List<Project> mProjectList;

    public projectListAdapter(Context context) {mInflater = LayoutInflater.from(context); }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.project_item, parent, false);
        return new ProjectViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        if (mProjectList != null) {
            Project current = mProjectList.get(position);
            holder.titleView.setText(current.getName());
            holder.description.setText(current.getDescription());
        } else {
            holder.titleView.setText("Test");
            holder.description.setText("Test Project");
        }
    }

    public void setProjectList(List<Project> projects){
        mProjectList = projects;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mProjectList != null)
            return mProjectList.size();
        else return 0;
    }

    private void checkDelete(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.DialogTheme);
        builder.setTitle("Are you sure you want to delete this project");

        // Set up the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context, "Project Deleted", Toast.LENGTH_SHORT).show();
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

package edu.tamu.richardcouperthwaite.writinglog.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;

public class projectListAdapter extends RecyclerView.Adapter<projectListAdapter.ProjectViewHolder> {

    class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView titleView;
        private final TextView description;
        private final ImageButton startSession;


        private ProjectViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tvProjectTitle);
            description = itemView.findViewById(R.id.tvProjectOverview);
            startSession = itemView.findViewById(R.id.ibstart);

            startSession.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == startSession.getId()) {
                Toast.makeText(view.getContext(), "Start Session = "+ String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
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
}

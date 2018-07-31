package edu.tamu.richardcouperthwaite.writinglog.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.activities.WritingSession;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;


public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    List<Project> projects;
    Context context;

    public ProjectRecyclerViewAdapter(Context context, List<Project> movies){
        this.projects = movies;
        this.context = context;

    }

    private Context getContext(){
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Project project = projects.get(position);

        holder.tvTitle.setText(project.getTitle());
        holder.tvOverview.setText(project.getOverview());


    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tvProjectTitle)
        TextView tvTitle;
        @BindView(R.id.tvProjectOverview)
        TextView tvOverview;
        @BindView(R.id.cvProjectList)
        CardView cvMovie;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

        }

        @OnClick(R.id.ibstart)
        public void startSesswithProject() {
            Project project = projects.get(getAdapterPosition());

            Intent intent = new Intent(getContext(), WritingSession.class);
            intent.putExtra("PROJECT", project);
            getContext().startActivity(intent);
        }
    }
}

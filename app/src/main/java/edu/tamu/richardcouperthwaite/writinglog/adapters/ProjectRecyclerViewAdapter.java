package edu.tamu.richardcouperthwaite.writinglog.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Project;


public class ProjectRecyclerViewAdapter extends RecyclerView.Adapter<ProjectRecyclerViewAdapter.ViewHolder> {

    List<Project> movies;
    Context context;

    public ProjectRecyclerViewAdapter(Context context, List<Project> movies){
        this.movies = movies;
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

        Project project = movies.get(position);

        holder.tvTitle.setText(project.getTitle());
        holder.tvOverview.setText(project.getOverview());


    }

    @Override
    public int getItemCount() {
        return movies.size();
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
    }
}

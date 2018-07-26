package edu.tamu.richardcouperthwaite.writinglog.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.project;

public class project_view_adapter extends RecyclerView.Adapter<project_view_adapter.ViewHolder> {

    List<project> projects;
    Context context;

    public project_view_adapter(Context context, List<project> projects) {
        this.projects = projects;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.projectitem, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        project proj = projects.get(position);
        holder.tvTitle.setText(proj.getTitle());
        holder.tvDescription.setText(proj.getDescription());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvprojectTitle)
        TextView tvTitle;
        @BindView(R.id.tvprojectDescription)
        TextView tvDescription;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


}

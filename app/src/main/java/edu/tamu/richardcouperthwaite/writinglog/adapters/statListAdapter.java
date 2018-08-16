package edu.tamu.richardcouperthwaite.writinglog.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.tamu.richardcouperthwaite.writinglog.R;
import edu.tamu.richardcouperthwaite.writinglog.models.Statistics;


public class statListAdapter extends RecyclerView.Adapter<statListAdapter.StatsViewHolder> {
    class StatsViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleView;
        private final TextView valueView;

        private StatsViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tvProjectTitle);
            valueView = itemView.findViewById(R.id.tvProjectOverview);
        }
    }

    private final LayoutInflater mInflater;
    private List<Statistics> mStatList;

    public statListAdapter(Context context) {mInflater = LayoutInflater.from(context); }

    @Override
    public StatsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.project_item, parent, false);
        return new StatsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StatsViewHolder holder, int position) {
        if (mStatList != null) {
            Statistics current = mStatList.get(position);
            holder.titleView.setText(current.getTitle());
            holder.valueView.setText(String.format("%d", current.getValue()));
        } else {
            holder.titleView.setText("No Title");
            holder.valueView.setText("0");
        }
    }

    public void setStatList(List<Statistics> stats){
        mStatList = stats;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mStatList != null)
            return mStatList.size();
        else return 0;
    }
}

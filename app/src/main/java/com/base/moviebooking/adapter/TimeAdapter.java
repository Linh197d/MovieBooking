package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.ViewholderTimesBinding;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.ui.schedule.ScheduleCinemaModel;

public class TimeAdapter extends EndlessLoadingRecyclerViewAdapter<ViewholderTimesBinding> {
    ScheduleCinemaModel scheduleCinemaModel;
    private Context mContext;
    private OnChooseRecyclerView mOnChooseRecyclerView;

    public TimeAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.mOnChooseRecyclerView = onChooseRecyclerView;
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewholderTimesBinding binding, ViewGroup parent) {
        return new TimeViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        TimeViewHolder timeViewHolder = (TimeViewHolder) holder;
        timeViewHolder.bind(getItem(position, Schedule.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.viewholder_times;
    }

    public class TimeViewHolder extends NormalViewHolder<Schedule> {
        private ViewholderTimesBinding binding;

        TimeViewHolder(ViewholderTimesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Schedule data) {
            String s = data.getPremiere().toString().substring(11, 16);
            s = changeTimeZone(s);
            binding.timeText.setText(s);
            binding.listTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnChooseRecyclerView.onChooseTime(data);
                }
            });
        }

        public String changeTimeZone(String s) {
            String s1 = s.substring(0, 2);
            String s2 = s.substring(3, 5);
            int i = Integer.parseInt(s1) + 7;
            if (i >= 24) {
                i = i - 24;
            }
            s = i + ":" + s2;
            return s;
        }
    }
}

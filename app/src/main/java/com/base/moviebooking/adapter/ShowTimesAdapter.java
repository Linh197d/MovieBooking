package com.base.moviebooking.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvLichchieuBinding;
import com.base.moviebooking.entity.Schedule;

public class ShowTimesAdapter extends EndlessLoadingRecyclerViewAdapter<RcvLichchieuBinding> {
    private final Context mContext;
    private OnChooseRecyclerView mOnChooseRecyclerView;

    public ShowTimesAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.mOnChooseRecyclerView = onChooseRecyclerView;
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvLichchieuBinding binding, ViewGroup parent) {
        return new LichChieuViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        LichChieuViewHolder searchViewHolder = (LichChieuViewHolder) holder;
        searchViewHolder.bind(getItem(position, Schedule.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_lichchieu;
    }


    public class LichChieuViewHolder extends NormalViewHolder<Schedule> {
        private final RcvLichchieuBinding binding;

        LichChieuViewHolder(RcvLichchieuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Schedule data) {
            binding.setLichchieu(data);
            String s = data.getPremiere().toString().substring(11,16);
            Log.d("fat",s);
            s=changeTimeZone(s);
            binding.check.setText(s);

            binding.lnCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnChooseRecyclerView.onChooseLichChieu(data);
                }
            });
        }

    }
    public String changeTimeZone(String s){
        String s1 = s.substring(0,2);
        String s2 = s.substring(3,5);
        int i = Integer.parseInt(s1)+7;
        if(i>=24){
            i = i-24;
        }
        s= i+":"+s2;
        return s;
    }
}


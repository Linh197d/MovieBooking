package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvDienanhBinding;
import com.base.moviebooking.databinding.RcvSeatBinding;
import com.base.moviebooking.entity.DienAnh;
import com.base.moviebooking.entity.Seat;
import com.bumptech.glide.Glide;

public class SeatAdapter extends EndlessLoadingRecyclerViewAdapter<RcvSeatBinding> {
    private Context mContext;
    private OnChooseRecyclerView chooseRecyclerView;
    public SeatAdapter(Context context, boolean enableSelectedMode, Context mContext,OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.chooseRecyclerView = onChooseRecyclerView;
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvSeatBinding binding, ViewGroup parent) {
        return new SeatViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        SeatViewHolder searchViewHolder = (SeatViewHolder) holder;
        searchViewHolder.bind(getItem(position, Seat.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_seat;
    }


    public class SeatViewHolder extends NormalViewHolder<Seat> {
        private RcvSeatBinding binding;

        SeatViewHolder(RcvSeatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Seat data) {


            binding.setSeat(data);
//            Log.d("fat","datâRap"+data.getUrlImage(),null);
        }
    }

}

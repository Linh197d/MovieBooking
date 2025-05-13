package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvSeatBinding;
import com.base.moviebooking.entity.Chair;
import com.base.moviebooking.entity.Seat;
import com.base.moviebooking.listener.OnChooseSeat;

import java.util.List;

public class SeatAdapter extends EndlessLoadingRecyclerViewAdapter<RcvSeatBinding> {
    private Context mContext;
    private OnChooseSeat mOnChooseSeat;
    private List<Chair> chairList;

    public SeatAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseSeat onChooseRecyclerView, List<Chair> list) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.mOnChooseSeat = onChooseRecyclerView;
        this.chairList = list;
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
//            Log.d("fat","chairList:"+chairList.get(0).getChair_id());
            for (int i = 0; i < chairList.size(); i++) {
                if (chairList.get(i).getChair_id() == data.getId()) {
                    binding.frSeat.setBackgroundResource(R.drawable.custom_selected_seat);
                    binding.frSeat.setEnabled(false);
                }
            }
            binding.tvtChair.setText(data.getxPosition() + data.getyPosition());
            binding.setSeat(data);
//            Log.d("fat","datâRap"+data.getUrlImage(),null);
            binding.frSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (data.getChoosed()) {
                        data.setChoosed(false);
                        binding.frSeat.setBackgroundResource(R.drawable.custom_seat_frame);
                    } else {
                        data.setChoosed(true);
                        binding.frSeat.setBackgroundResource(R.drawable.choose_ghe);
                    }
                    mOnChooseSeat.onSeat(data);
                }
            });
            if (data.getStatus() == 1) {
                binding.imgSeat.setColorFilter(ContextCompat.getColor(getContext(), R.color.red));
            }
        }
    }

}

package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvRapBinding;
import com.base.moviebooking.entity.Rap;
import com.base.moviebooking.ui.rapphim.RapViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RapAdapter extends EndlessLoadingRecyclerViewAdapter<RcvRapBinding> {
    private Context mContext;

    public RapAdapter(Context context, boolean enableSelectedMode, Context mContext) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvRapBinding binding, ViewGroup parent) {
        return new RapViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        RapViewHolder searchViewHolder = (RapViewHolder) holder;
        searchViewHolder.bind(getItem(position, Rap.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_rap;
    }


    public class RapViewHolder extends NormalViewHolder<Rap> {
        private RcvRapBinding binding;

        RapViewHolder(RcvRapBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Rap data) {


        }
    }
}
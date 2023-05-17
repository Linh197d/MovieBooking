package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.base.RecyclerViewAdapter;
import com.base.moviebooking.databinding.HomeFragmentBinding;
import com.base.moviebooking.databinding.RcvPhimHomeBinding;
import com.base.moviebooking.entity.Phim;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeAdapter extends EndlessLoadingRecyclerViewAdapter<RcvPhimHomeBinding> {
    private Context mContext;


    public HomeAdapter(Context context, boolean enableSelectedMode, Context mContext) {
        super(context, enableSelectedMode);
        this.mContext = context;

    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvPhimHomeBinding binding, ViewGroup parent) {
        return new PhimViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        PhimViewHolder searchViewHolder = (PhimViewHolder) holder;
        searchViewHolder.bind(getItem(position, Phim.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_phim_home;
    }


    public class PhimViewHolder extends NormalViewHolder<Phim> {
        private RcvPhimHomeBinding binding;

        PhimViewHolder(RcvPhimHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Phim data) {

//            if (data.getAvatar() != null) {
//                Glide.with(mContext)
//                        .load(BASE_STORAGE + data.getAvatar())
//                        .into(binding.avatarDoctor);
//            }
//            if (data.getCreated_by() != 2) {
//                binding.lskbOut.setVisibility(View.GONE);
//                binding.deleteLSKBIn.setVisibility(View.VISIBLE);
//            } else {
//                binding.lskbOut.setVisibility(View.GONE);
//                binding.deleteLSKBIn.setVisibility(View.GONE);
//            }


        }
    }
}

package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvRapBinding;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.bumptech.glide.Glide;

public class TheaterAdapter extends EndlessLoadingRecyclerViewAdapter<RcvRapBinding> {
    private final Context mContext;
    private OnChooseRecyclerView mOnChooseRecyclerView;

    public TheaterAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = context;
        this.mOnChooseRecyclerView = onChooseRecyclerView;
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvRapBinding binding, ViewGroup parent) {
        return new RapViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        RapViewHolder searchViewHolder = (RapViewHolder) holder;
        searchViewHolder.bind(getItem(position, Theater.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_rap;
    }


    public class RapViewHolder extends NormalViewHolder<Theater> {
        private final RcvRapBinding binding;

        RapViewHolder(RcvRapBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Theater data) {
            binding.setTheater(data);
//            Glide.with(mContext)
//                    .load(data.getUrlImage())
//                    .into(binding.image);
//            Log.d("fat", "datâRap" + data.getUrlImage(), null);
            if (data.getUrlImage() != null && !data.getUrlImage().isEmpty()) {
                Glide.with(mContext)
                        .load(data.getUrlImage())
                        .into(binding.image);
            } else {
                // Nếu không có đường dẫn ảnh thì có thể hiển thị ảnh mặc định hoặc ẩn ImageView
                binding.image.setImageResource(R.drawable.rap4);
                // hoặc
                // binding.actorImage.setVisibility(View.GONE);
            }

            binding.lnTheater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnChooseRecyclerView.onChooseRap(data);
                }
            });
        }
    }
}
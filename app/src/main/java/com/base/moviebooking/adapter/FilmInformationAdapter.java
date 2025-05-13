package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvDienanhBinding;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.bumptech.glide.Glide;

public class FilmInformationAdapter extends EndlessLoadingRecyclerViewAdapter<RcvDienanhBinding> {
    private Context mContext;
    private OnChooseRecyclerView mOnChooseRecyclerView;

    public FilmInformationAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.mOnChooseRecyclerView = onChooseRecyclerView;
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvDienanhBinding binding, ViewGroup parent) {
        return new DienAnhViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        DienAnhViewHolder searchViewHolder = (DienAnhViewHolder) holder;
        searchViewHolder.bind(getItem(position, FilmInfo.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_dienanh;
    }


    public class DienAnhViewHolder extends NormalViewHolder<FilmInfo> {
        private RcvDienanhBinding binding;

        DienAnhViewHolder(RcvDienanhBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(FilmInfo data) {

            Glide.with(mContext)
                    .load(data.getUrlImage())
                    .into(binding.image);
            binding.setDienanh(data);
            binding.lnFilminfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnChooseRecyclerView.onChooseFilmInfo(data);

                }
            });
//            Log.d("fat","datâRap"+data.getUrlImage(),null);
        }
    }
}
package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.RcvDienanhBinding;
import com.base.moviebooking.entity.DienAnh;
import com.bumptech.glide.Glide;

public class DienAnhAdapter extends EndlessLoadingRecyclerViewAdapter<RcvDienanhBinding> {
    private Context mContext;
    private OnChooseRecyclerView chooseRecyclerView;
    public DienAnhAdapter(Context context, boolean enableSelectedMode, Context mContext,OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.chooseRecyclerView = onChooseRecyclerView;
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvDienanhBinding binding, ViewGroup parent) {
        return new DienAnhViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        DienAnhViewHolder searchViewHolder = (DienAnhViewHolder) holder;
        searchViewHolder.bind(getItem(position, DienAnh.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_dienanh;
    }


    public class DienAnhViewHolder extends NormalViewHolder<DienAnh> {
        private RcvDienanhBinding binding;

        DienAnhViewHolder(RcvDienanhBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(DienAnh data) {

            Glide.with(mContext)
                    .load( data.getUrlImage())
                    .into(binding.image);
            binding.setDienanh(data);
//            Log.d("fat","datâRap"+data.getUrlImage(),null);
        }
    }
}
package com.base.mvvmbaseproject.adapter;

import static com.base.mvvmbaseproject.BuildConfig.BASE_STORAGE;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvmbaseproject.OnChooseRecyclerView;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.EndlessLoadingRecyclerViewAdapter;
import com.base.mvvmbaseproject.databinding.DataLskbBinding;
import com.base.mvvmbaseproject.entity.LSKhamBenh;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LSKhamBenhAdapter extends EndlessLoadingRecyclerViewAdapter<DataLskbBinding> {
    private Context mContext;
    int row_index = -1;
    OnChooseRecyclerView onChooseRecyclerView;
    private ArrayList<LSKhamBenh> mLsKhamBenh = new ArrayList<>();

    public LSKhamBenhAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView onChooseRecyclerView, ArrayList<LSKhamBenh> mLsKhamBenh) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.onChooseRecyclerView = onChooseRecyclerView;
        this.mLsKhamBenh = mLsKhamBenh;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(DataLskbBinding binding, ViewGroup parent) {
        return new SearchViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
        searchViewHolder.bind(getItem(position, LSKhamBenh.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.data_lskb;
    }

    public int getRow_index() {
        return row_index;
    }

    public class SearchViewHolder extends NormalViewHolder<LSKhamBenh> {
        private DataLskbBinding binding;

        SearchViewHolder(DataLskbBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(LSKhamBenh data) {
            if (data.getAvatar() != null) {
                Glide.with(mContext)
                        .load(BASE_STORAGE + data.getAvatar())
                        .into(binding.avatarDoctor);
            }
            if (data.getCreated_by() != 2) {
                binding.lskbOut.setVisibility(View.GONE);
                binding.deleteLSKBIn.setVisibility(View.VISIBLE);
            }
            else{
                binding.lskbOut.setVisibility(View.GONE);
                binding.deleteLSKBIn.setVisibility(View.GONE);
            }
            binding.setSearch(data);
            binding.deleteLSKBIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.deleteLSKBIn.setVisibility(View.GONE);
                    binding.lskbOut.setVisibility(View.VISIBLE);
                }
            });
            binding.deleteLSKBOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.lskbOut.setVisibility(View.GONE);
                    binding.deleteLSKBIn.setVisibility(View.VISIBLE);
                }
            });
            binding.deleteLskb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onChooseRecyclerView.onChooseLSKB(data);
                    binding.lskbOut.setVisibility(View.GONE);
                    binding.deleteLSKBIn.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
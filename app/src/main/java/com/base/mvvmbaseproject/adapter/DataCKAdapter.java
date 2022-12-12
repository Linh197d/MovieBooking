package com.base.mvvmbaseproject.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.EndlessLoadingRecyclerViewAdapter;
import com.base.mvvmbaseproject.databinding.DataCkBinding;
import com.base.mvvmbaseproject.entity.DataServicesCK;

public class DataCKAdapter extends EndlessLoadingRecyclerViewAdapter<DataCkBinding> {
    public DataCKAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(DataCkBinding binding, ViewGroup parent) {
        return new DataCKAdapter.SearchViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        DataCKAdapter.SearchViewHolder searchViewHolder = (DataCKAdapter.SearchViewHolder) holder;
        searchViewHolder.bind(getItem(position, DataServicesCK.class));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.data_ck;
    }

    public class SearchViewHolder extends NormalViewHolder<DataServicesCK> {
        private DataCkBinding binding;

        SearchViewHolder(DataCkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(DataServicesCK data) {
            binding.setSearchCK(data);
        }
    }
}

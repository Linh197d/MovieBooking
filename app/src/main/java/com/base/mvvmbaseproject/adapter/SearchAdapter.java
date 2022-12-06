package com.base.mvvmbaseproject.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.EndlessLoadingRecyclerViewAdapter;
import com.base.mvvmbaseproject.databinding.ItemSearchBinding;
import com.base.mvvmbaseproject.entity.SearchResponse;
import com.bumptech.glide.Glide;

public class SearchAdapter extends EndlessLoadingRecyclerViewAdapter<ItemSearchBinding> {

    public SearchAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemSearchBinding binding, ViewGroup parent) {
        return new SearchViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
        searchViewHolder.bind(getItem(position, SearchResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_search;
    }

    public class SearchViewHolder extends NormalViewHolder<SearchResponse> {
        private ItemSearchBinding binding;

        SearchViewHolder(ItemSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(SearchResponse data) {
            binding.setSearch(data);
        }
    }
}

package com.base.mvvmbaseproject.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.EndlessLoadingRecyclerViewAdapter;
import com.base.mvvmbaseproject.databinding.CountNotifyFragmentBinding;
import com.base.mvvmbaseproject.databinding.DataCkBinding;
import com.base.mvvmbaseproject.databinding.RcvCountNotifyBinding;
import com.base.mvvmbaseproject.entity.CountNotify;
import com.base.mvvmbaseproject.entity.DataServicesCK;

import java.util.ArrayList;

public class CountNotifyAdapter extends EndlessLoadingRecyclerViewAdapter<RcvCountNotifyBinding>{
    public CountNotifyAdapter(Context context, ArrayList<CountNotify> mCountNotify) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(RcvCountNotifyBinding binding, ViewGroup parent) {
        return new CountNotifyAdapter.SearchViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        CountNotifyAdapter.SearchViewHolder searchViewHolder = (CountNotifyAdapter.SearchViewHolder) holder;
        searchViewHolder.bind(getItem(position, CountNotify.class));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.rcv_count_notify;
    }

    public class SearchViewHolder extends NormalViewHolder<CountNotify> {
        private RcvCountNotifyBinding binding;

        SearchViewHolder(RcvCountNotifyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(CountNotify data) {
            binding.setCountnotify(data);
        }
    }
}


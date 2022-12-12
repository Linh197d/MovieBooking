package com.base.mvvmbaseproject.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.EndlessLoadingRecyclerViewAdapter;
import com.base.mvvmbaseproject.databinding.DataCkBinding;
import com.base.mvvmbaseproject.databinding.UserProfileBinding;
import com.base.mvvmbaseproject.entity.DataServicesCK;
import com.base.mvvmbaseproject.entity.DataUser;

public class UserAdapter extends EndlessLoadingRecyclerViewAdapter<UserProfileBinding> {
    public UserAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(UserProfileBinding binding, ViewGroup parent) {
        return new UserAdapter.SearchViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        UserAdapter.SearchViewHolder searchViewHolder = (UserAdapter.SearchViewHolder) holder;
        searchViewHolder.bind(getItem(position, DataUser.class));

    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_profile;
    }

    public class SearchViewHolder extends NormalViewHolder<DataUser> {
        private UserProfileBinding binding;

        SearchViewHolder(UserProfileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(DataUser data) {
            binding.setSearchUser(data);
        }
    }
}

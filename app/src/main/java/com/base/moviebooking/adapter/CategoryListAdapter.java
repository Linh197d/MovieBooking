package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.ViewholderCategoryBinding;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.listener.OnChooseRecyclerView;

public class CategoryListAdapter extends EndlessLoadingRecyclerViewAdapter<ViewholderCategoryBinding> {

    private Context mContext;
    private OnChooseRecyclerView mOnChooseRecyclerView;
    public CategoryListAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.mOnChooseRecyclerView = onChooseRecyclerView;
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewholderCategoryBinding binding, ViewGroup parent) {
        return new CategoryViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
       CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
        categoryViewHolder.bind(getItem(position, Category.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.viewholder_category;
    }

    public class CategoryViewHolder extends NormalViewHolder<Category> {
        private ViewholderCategoryBinding binding;

        CategoryViewHolder(ViewholderCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Category data) {
        binding.titleTxt.setText(data.getName().toString());
            binding.listCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnChooseRecyclerView.onChooseCategory(data);
                }
            });
        }
    }
}

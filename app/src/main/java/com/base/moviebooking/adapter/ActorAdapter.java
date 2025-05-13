package com.base.moviebooking.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.base.EndlessLoadingRecyclerViewAdapter;
import com.base.moviebooking.databinding.ViewholderActorsBinding;
import com.base.moviebooking.entity.Actor;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.bumptech.glide.Glide;

public class ActorAdapter extends EndlessLoadingRecyclerViewAdapter<ViewholderActorsBinding> {
    private Context mContext;
    private OnChooseRecyclerView mOnChooseRecyclerView;

    public ActorAdapter(Context context, boolean enableSelectedMode, Context mContext, OnChooseRecyclerView onChooseRecyclerView) {
        super(context, enableSelectedMode);
        this.mContext = mContext;
        this.mOnChooseRecyclerView = onChooseRecyclerView;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewholderActorsBinding binding, ViewGroup parent) {
        return new ActorViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        ActorViewHolder actorViewHolder = (ActorViewHolder) holder;
        actorViewHolder.bind(getItem(position, Actor.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.viewholder_actors;
    }

    public class ActorViewHolder extends NormalViewHolder<Actor> {
        private ViewholderActorsBinding binding;

        ActorViewHolder(ViewholderActorsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(Actor data) {
            binding.actorName.setText(data.getName().toString());
            if (data.getImage() != null && !data.getImage().isEmpty()) {
                Glide.with(mContext)
                        .load(data.getImage())
                        .into(binding.actorImage);
            } else {
                // Nếu không có đường dẫn ảnh thì có thể hiển thị ảnh mặc định hoặc ẩn ImageView
                binding.actorImage.setImageResource(R.drawable.dv1);
                // hoặc
                // binding.actorImage.setVisibility(View.GONE);
            }
        }
    }
}

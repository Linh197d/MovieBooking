package com.base.moviebooking.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.base.moviebooking.ui.comments.CommentFragment;
import com.base.moviebooking.ui.detail_movie.DetailMovieFragment;
import com.base.moviebooking.ui.show_time_child.ShowTimeChildFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ShowTimeChildFragment();
            case 1:
                return new DetailMovieFragment();
            case 2:
                return new CommentFragment();
            default:
                return new ShowTimeChildFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}

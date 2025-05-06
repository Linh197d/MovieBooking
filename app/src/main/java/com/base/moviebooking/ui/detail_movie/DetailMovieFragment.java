package com.base.moviebooking.ui.detail_movie;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ThongtinFragmentBinding;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.ui.show_time.ShowTimeViewModel;

public class DetailMovieFragment extends BaseFragment<ThongtinFragmentBinding> {
    ShowTimeViewModel showTimeViewModel;

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
//        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailMovieViewModel.class);
        showTimeViewModel = ViewModelProviders.of(requireParentFragment()).get(ShowTimeViewModel.class);
        showTimeViewModel.getData().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie s) {
                Log.d("transfer", "sendata observable");
                binding.descriptionMovie.setText(s.getDescription());
                binding.directorMovie.setText(s.getDirector());
//                        binding.descriptionMovie.setText(s.getTime());
//                        binding.descriptionMovie.setText(s.getTimeRelease());
            }
        });
    }

    @Override
    public void initData() {


    }

    @NonNull
    @Override
    public ThongtinFragmentBinding getViewBinding() {
        return ThongtinFragmentBinding.inflate(getLayoutInflater());
    }
}

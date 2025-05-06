package com.base.moviebooking.ui.film_info;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.R;
import com.base.moviebooking.databinding.FilmInfoChildFragmentBinding;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.ui.home.HomeFragment;

public class FilmInfoChildFragment extends BaseFragment<FilmInfoChildFragmentBinding> {
    private FilmInfoViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.film_info_child_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }


    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(FilmInfoViewModel.class);
        Bundle bundle = getArguments();

        if (bundle != null && !bundle.isEmpty()) {
            FilmInfo filmInfo = (FilmInfo) bundle.getSerializable("filmInfo");
            // Xử lý dữ liệu trong bundle
            binding.img1.setImageResource(filmInfo.getUrlImage());
            binding.img2.setImageResource(filmInfo.getUrlImage());
            binding.tvtTop.setText(filmInfo.getTenDienAnh().toString());
            binding.tvt1.setText(filmInfo.getBaiviet());
            binding.tvt2.setText(filmInfo.getBaiviet2());
            getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void initData() {
        getActivity().findViewById(R.id.img_headerphim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.backFromAddFragment(null);
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
            }
        });
        binding.btnDatveNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.replaceFragment(HomeFragment.class, null);
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);

            }
        });
        TextView t = getActivity().findViewById(R.id.tvt_headerphim);
        t.setText("Review Phim");
    }


}


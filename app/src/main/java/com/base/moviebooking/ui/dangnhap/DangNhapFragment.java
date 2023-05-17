package com.base.moviebooking.ui.dangnhap;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.DangnhapFragmentBinding;
import com.base.moviebooking.databinding.SplashFragmentBinding;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.splash.SplashViewModel;

public class DangNhapFragment extends BaseFragment<DangnhapFragmentBinding> {

    private DangNhapViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.dangnhap_fragment;
    }

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
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(DangNhapViewModel.class);
        binding.backDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fat","back dky",null);
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                mViewController.backFromAddFragment(null);
            }

        });
    }

    @Override
    public void initData() {

    }


}


package com.base.moviebooking.ui.splash;

import android.os.Handler;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.DataLocalManager;
import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.SplashFragmentBinding;
import com.base.moviebooking.ui.home.HomeFragment;


public class SplashFragment extends BaseFragment<SplashFragmentBinding> {

    private SplashViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment;
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
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(SplashViewModel.class);
    }

    @Override
    public void initData() {
        new Handler().postDelayed(()->{
            mViewController.replaceFragment(HomeFragment.class,null);
        },2000);
    }


}

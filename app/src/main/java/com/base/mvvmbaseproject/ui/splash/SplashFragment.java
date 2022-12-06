package com.base.mvvmbaseproject.ui.splash;

import android.os.Handler;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.base.mvvmbaseproject.DataLocalManager;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.databinding.SplashFragmentBinding;
import com.base.mvvmbaseproject.ui.home.HomeFragment;
import com.base.mvvmbaseproject.ui.login.LoginFragment;
import com.base.mvvmbaseproject.ui.main.MainActivity;


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
        if (DataLocalManager.getInstance() != null && DataLocalManager.getBooleanValue()) {
            new Handler().postDelayed(() -> {
                mViewController.addFragment(HomeFragment.class, null);
            }, 2000);
        }else {
            new Handler().postDelayed(() -> {
                mViewController.addFragment(LoginFragment.class, null);
            }, 2000);
        }
    }

}

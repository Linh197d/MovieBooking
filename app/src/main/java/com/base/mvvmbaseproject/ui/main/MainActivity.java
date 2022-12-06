package com.base.mvvmbaseproject.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvmbaseproject.DataLocalManager;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.BaseActivity;
import com.base.mvvmbaseproject.databinding.ActivityMainBinding;
import com.base.mvvmbaseproject.ui.UserProfile.UserFragment;
import com.base.mvvmbaseproject.ui.home.HomeFragment;
import com.base.mvvmbaseproject.ui.login.LoginFragment;
import com.base.mvvmbaseproject.ui.splash.SplashFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.flMainContainer;
    }

    @Override
    public void initView() {
        mViewController.addFragment(SplashFragment.class, null);
    }

    @Override
    public void initData() {
        /*binding.btnHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(UserFragment.class,null);
            }
        });*/
    }
}

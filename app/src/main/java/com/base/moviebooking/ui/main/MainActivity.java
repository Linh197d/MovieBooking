package com.base.moviebooking.ui.main;

import android.view.LayoutInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseActivity;
import com.base.moviebooking.base.ViewController;
import com.base.moviebooking.databinding.ActivityMainBinding;
import com.base.moviebooking.ui.account.AccountFragment;
import com.base.moviebooking.ui.film_info.FilmInfoFragment;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.splash.SplashFragment;
import com.base.moviebooking.ui.theater.TheaterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getFragmentContainerId() {
        return R.id.frame_layout_parent;
    }

    public ViewController getViewController() {
        return mViewController;
    }

    @Override
    public void initView() {

        mViewController.addFragment(SplashFragment.class, null);

        binding.bottombar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.btnTrangchu) {
                    mViewController.replaceFragment(HomeFragment.class, null);
                } else if (itemId == R.id.btnRapPhim) {
                    mViewController.replaceFragment(TheaterFragment.class, null);
                } else if (itemId == R.id.btnDienAnh) {
                    mViewController.replaceFragment(FilmInfoFragment.class, null);
                } else if (itemId == R.id.btnTaiKhoan) {
                    mViewController.replaceFragment(AccountFragment.class, null);
                }
                return true;
            }
        });
    }

    @NonNull
    @Override
    public ActivityMainBinding setBinding(@NonNull LayoutInflater layoutInflater) {
        return ActivityMainBinding.inflate(layoutInflater);
    }

    @Override
    public void initEvent() {

    }
}

package com.base.moviebooking.ui.main;

import android.graphics.Color;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseActivity;
import com.base.moviebooking.base.ViewController;
import com.base.moviebooking.databinding.ActivityMainBinding;
import com.base.moviebooking.ui.account.AccountFragment;
import com.base.moviebooking.ui.film_info.FilmInfoFragment;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.splash.SplashFragment;
import com.base.moviebooking.ui.theater.TheaterFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getFragmentContainerId() {
        return R.id.frame_layout_parent;
    }

    public ViewController getViewController(){
        return mViewController ;
    }
    @Override
    public void initView() {

        mViewController.addFragment(SplashFragment.class, null);
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottombar);

// Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.trangchu, R.drawable.home2, R.color.colorStrokeBlue);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.rapphim, R.drawable.rap2, R.color.colorStrokeBlue);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.dienanh, R.drawable.movieglass, R.color.colorStrokeBlue);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.hoso, R.drawable.user2, R.color.colorStrokeBlue);

// Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        // Set background color
        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
//        // Use colored navigation with circle reveal effect
//        bottomNavigation.setColored(true);
        // Change colors
        bottomNavigation.setAccentColor(Color.parseColor("#FFFF9800"));
//        bottomNavigation.setInactiveColor(Color.parseColor("#4BAEBD"));
//        // Add or remove notification for each item
//        bottomNavigation.setNotification("1", 3);
//// OR
//        AHNotification notification = new AHNotification.Builder()
//                .setText("1")
//                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorStrokeBlue))
//                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.colorStrokeBlue))
//                .build();
//        bottomNavigation.setNotification(notification, 1);
// Manage titles
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
//        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);
//        // Enable / disable item & set disable color
//        bottomNavigation.enableItemAtPosition(2);
//        bottomNavigation.disableItemAtPosition(2);
//        bottomNavigation.setItemDisableColor(Color.parseColor("#3A000000"));
//
        bottomNavigation.setTitleTextSizeInSp(17,14);

// Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position){
                    case 0:
                        mViewController.replaceFragment(HomeFragment.class,null);
                        break;
                    case 1:
                        mViewController.replaceFragment(TheaterFragment.class,null);
                        break;

                    case 2:
                        mViewController.replaceFragment(FilmInfoFragment.class,null);
                        break;

                    case 3:
                        mViewController.replaceFragment(AccountFragment.class,null);
                        break;

                }
                return true;
            }
        });
    }

    @Override
    public void initData() {



    }
}

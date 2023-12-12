package com.base.moviebooking.ui.show_time;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.palette.graphics.Palette;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.ViewPagerAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.LichPhimBinding;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.ui.home.HomeFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class ShowTimeFragment extends BaseFragment<LichPhimBinding>  {
    public final String TAG = "fat";
    private ShowTimeViewModel mViewModel;
    private ViewPagerAdapter viewPagerAdapter;
    Movie movie;
    @Override
    protected int getLayoutId() {
        return R.layout.lich_phim;
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
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ShowTimeViewModel.class);
        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewpagerLichphim.setAdapter(viewPagerAdapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewpagerLichphim, new TabLayoutMediator.TabConfigurationStrategy() {

            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Lịch chiếu");
                        break;
                    case 1:
                        tab.setText("Thông tin");
                        break;
                }
            }
        }).attach();
        //toolbar
        activity.setSupportActionBar(binding.toolbar);
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //tablayout animation
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.antman);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(@Nullable Palette palette) {
//                int myColor = palette.getVibrantColor(getResources().getColor(R.color.colorStrokeBlue));
//                int myDarkColor = palette.getVibrantColor(getResources().getColor(R.color.colorBlack));
//                binding.collapsingLayout.setContentScrimColor(myColor);
//                binding.collapsingLayout.setStatusBarScrimColor(myDarkColor);
//
//            }
//        });

        setDataView();
    }

    @Override
    public void initData() {
        eventClick();
    }

    private void eventClick() {
        // set event click back
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                mViewController.replaceFragment(HomeFragment.class,null);
            }
        });

    }

    @Override
    public void onResume() {
        Log.d(TAG,"onResume Showtime");
        setDataView();
        super.onResume();
    }

    private void setDataView() {
        Bundle bundle = getArguments();
        Log.d("cather","detail");

        if (bundle != null && !bundle.isEmpty()) {
             movie = (Movie) bundle.getSerializable("movie");
            // Xử lý dữ liệu trong bundle
            mViewModel.sendData(movie);
            switch (movie.getId()){
                case 1:
                    binding.imgMovie.setImageResource(R.drawable.lastofus);
                    break;
                case 12:
                    binding.imgMovie.setImageResource(R.drawable.avatar);
                    break;
                case 13:
                    binding.imgMovie.setImageResource(R.drawable.pussinboots);
                    break;
                case 19:
                    binding.imgMovie.setImageResource(R.drawable.blackpanther);
                    break;
                case 20:
                    binding.imgMovie.setImageResource(R.drawable.teenwolf);
                    break;
                case 22:
                    binding.imgMovie.setImageResource(R.drawable.megan);
                    break;
                case 23:
                    binding.imgMovie.setImageResource(R.drawable.spiderman);
                    break;
                case 24:
                    binding.imgMovie.setImageResource(R.drawable.spiritedaway);
                    break;


            }

            binding.collapsingLayout.setTitle(movie.getName());
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lastofus);
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(@Nullable Palette palette) {
                    int myColor = palette.getVibrantColor(getResources().getColor(R.color.colorStrokeBlue));
                    int myDarkColor = palette.getVibrantColor(getResources().getColor(R.color.colorBlack));
                    binding.collapsingLayout.setContentScrimColor(myColor);
                    binding.collapsingLayout.setStatusBarScrimColor(myDarkColor);
                }
            });
        } else {
            // Không có dữ liệu để xử lý
            Log.d("cather","des NO data");
        }
    }

}

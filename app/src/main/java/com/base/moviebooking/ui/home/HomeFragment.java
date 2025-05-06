package com.base.moviebooking.ui.home;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.base.moviebooking.DataLocalManager;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.adapter.HomeAdapter;
import com.base.moviebooking.adapter.SlideAdapter;
import com.base.moviebooking.databinding.HomeFragmentBinding;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Slide;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.ui.main.MainActivity;
import com.base.moviebooking.ui.show_time.ShowTimeFragment;
import com.base.moviebooking.ui.sign_in.SignInFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends BaseFragment<HomeFragmentBinding> {
    private List<Slide> list;
    private List<Movie> movieList = new ArrayList<>();
    private SlideAdapter slideAdapter;
    private HomeAdapter homeAdapter;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (binding.viewpager.getCurrentItem() == list.size() - 1) {
                binding.viewpager.setCurrentItem(0);
            } else {
                binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
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
        getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        HomeViewModel mViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        //slide
        slideAdapter = new SlideAdapter(getContext(), getListSlide());
        binding.viewpager.setAdapter(slideAdapter);
        binding.circleIndicator.setViewPager(binding.viewpager);
        slideAdapter.registerDataSetObserver(binding.circleIndicator.getDataSetObserver());
        mHandler.postDelayed(mRunnable, 5000);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 5000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //bật dialog loading

        //phim home
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rcvSearch.setLayoutManager(gridLayoutManager);
        mViewModel.getData();
        homeAdapter = new HomeAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
            @Override
            public void onChoosePhim(Movie movie) {
                if (DataLocalManager.getInstance() != null && DataLocalManager.getBooleanValue()) {
                    Log.d("mmm", "home đã Login", null);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("movie", movie);
                    mViewController.addFragment(ShowTimeFragment.class, hashMap);
                } else {
                    Log.d("mmm", "home chưa Login", null);
                    getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
                    ((MainActivity) getActivity()).getViewController().replaceFragment(SignInFragment.class, null);

                }


            }

            @Override
            public void onChooseRap(Theater theater) {

            }

            @Override
            public void onChooseFilmInfo(FilmInfo filmInfo) {

            }

            @Override
            public void onChooseLichChieu(Schedule showTime) {

            }
        });
        mViewModel.dataMovie.observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieListResponse) {
                homeAdapter.addModels(movieListResponse, false);
                Log.d("fat", "add Model", null);
                getActivity().findViewById(R.id.dialog_load).setVisibility(View.GONE);
            }
        });
        binding.rcvSearch.setAdapter(homeAdapter);
    }


    @Override
    public void initData() {


    }

    private List<Slide> getListSlide() {
        list = new ArrayList<>();
        list.add(new Slide(R.drawable.avatar));
        list.add(new Slide(R.drawable.spiderman));
        list.add(new Slide(R.drawable.pussinboots));
        list.add(new Slide(R.drawable.lastofus));
        list.add(new Slide(R.drawable.megan));
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @NonNull
    @Override
    public HomeFragmentBinding getViewBinding() {
        return HomeFragmentBinding.inflate(getLayoutInflater());
    }
}

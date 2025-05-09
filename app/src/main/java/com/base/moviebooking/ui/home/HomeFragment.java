package com.base.moviebooking.ui.home;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.base.moviebooking.DataLocalManager;
import com.base.moviebooking.adapter.CategoryListAdapter;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.News;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.adapter.HomeAdapter;
import com.base.moviebooking.adapter.SlideAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.HomeFragmentBinding;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Slide;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.ui.main.MainActivity;
import com.base.moviebooking.ui.movie_by_categoryId.MovieByCategoryIdFragment;
import com.base.moviebooking.ui.search_film.SearchFilmFragment;
import com.base.moviebooking.ui.show_time.ShowTimeFragment;
import com.base.moviebooking.ui.sign_in.SignInFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class HomeFragment extends BaseFragment<HomeFragmentBinding> {
    private List<Slide> list;
    private List<Movie> movieList = new ArrayList<>();
    private List<Category> categoryList = new ArrayList<>();
    private SlideAdapter slideAdapter;
    private CategoryListAdapter categoryListAdapter;
    private ViewPager2 viewPager2;
    private HomeAdapter homeAdapter;
    private Handler mHandler = new Handler();
    private final Handler slideHandler= new Handler();
//    private Runnable mRunnable = new Runnable() {
//        @Override
//        public void run() {
//            if (binding.viewpager.getCurrentItem() == list.size() - 1) {
//                binding.viewpager.setCurrentItem(0);
//            } else {
//                binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem() + 1);
//            }
//        }
//    };
    @Override
    public void backFromAddFragment() {
    }

    @Override
    public boolean backPressed() {
        showDialogLogout();
        return false;
    }

    @Override
    public void initView() {
        getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        HomeViewModel mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        //slide
        banners();
//        slideAdapter = new SlideAdapter(getContext(), getListSlide());
//        binding.viewpager.setAdapter(slideAdapter);
//        binding.circleIndicator.setViewPager(binding.viewpager);
//        slideAdapter.registerDataSetObserver(binding.circleIndicator.getDataSetObserver());
//        mHandler.postDelayed(mRunnable, 5000);
//        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mHandler.removeCallbacks(mRunnable);
//                mHandler.postDelayed(mRunnable, 5000);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
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

            @Override
            public void onChooseCategory(Category category) {

            }

            @Override
            public void onChooseTime(Schedule schedule) {

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

        // Category
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.categoryView.setLayoutManager(linearLayoutManager);
        mViewModel.getListCategory();
        categoryListAdapter = new CategoryListAdapter(getContext(), false, getContext(),
                new OnChooseRecyclerView() {
                    @Override
                    public void onChoosePhim(Movie movie) {

                    }

                    @Override
                    public void onChooseRap(Theater theater) {

                    }

                    @Override
                    public void onChooseFilmInfo(FilmInfo filmInfo) {

                    }

                    @Override
                    public void onChooseLichChieu(Schedule schedule) {

                    }

                    @Override
                    public void onChooseCategory(Category category) {
                        if (DataLocalManager.getInstance() != null && DataLocalManager.getBooleanValue()) {
                            Log.d("mmm", "home đã Login", null);
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("category", category);
                            mViewController.addFragment(MovieByCategoryIdFragment.class,hashMap);
                        } else {
                            Log.d("mmm", "home chưa Login", null);
                            getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
                            ((MainActivity) getActivity()).getViewController().replaceFragment(SignInFragment.class, null);

                        }
                    }

                    @Override
                    public void onChooseTime(Schedule schedule) {

                    }
                });

        mViewModel.dataCategory.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categoriesListResponse) {
                categoryListAdapter.addModels(categoriesListResponse,false);
                Log.d("fat", "add Model Category", null);
                getActivity().findViewById(R.id.dialog_category).setVisibility(View.GONE);
            }
        });
        binding.categoryView.setAdapter(categoryListAdapter);
    }


    @Override
    public void initData() {
        binding.searchFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
                mViewController.addFragment(SearchFilmFragment.class,null);
            }
        });

    }

    ;
//    private List<Slide> getListSlide() {
//        list = new ArrayList<>();
//        list.add(new Slide(R.drawable.avatar));
//        list.add(new Slide(R.drawable.spiderman));
//        list.add(new Slide(R.drawable.pussinboots));
//        list.add(new Slide(R.drawable.lastofus));
//        list.add(new Slide(R.drawable.megan));
//        return list;
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mHandler.removeCallbacks(mRunnable);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
////        mHandler.postDelayed(mRunnable,3000);
//    }

    private void banners() {
        viewPager2 = binding.viewpager;
        list=  new ArrayList<>();
        list.add(new Slide(R.drawable.avatar));
        list.add(new Slide(R.drawable.pussinboots));
        list.add(new Slide(R.drawable.spiderman));
        list.add(new Slide(R.drawable.lastofus));
        list.add(new Slide(R.drawable.megan));
        list.add(new Slide(R.drawable.wide));
        list.add(new Slide(R.drawable.wide1));
        list.add(new Slide(R.drawable.wide3));
        viewPager2.setAdapter(
                new SlideAdapter(list,viewPager2)
        );
//        WormDotsIndicator dotsIndicator = binding.circleIndicator;
//        dotsIndicator.setViewPager2(viewPager2);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer= new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r= 1-Math.abs(position);
                page.setScaleY(0.70f+r*0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHandler.removeCallbacks(sliderRunnable);
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        slideHandler.removeCallbacks(sliderRunnable);
    }
    @Override
    public void onResume() {
        super.onResume();
        slideHandler.postDelayed(sliderRunnable,2000);
    }

    private final Runnable sliderRunnable= new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };
    private void showDialogLogout() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.msg_confirm_login_another_device))
                .setPositiveButton(getString(R.string.action_ok), (dialog, which) -> {
                    dialog.dismiss();
                    getActivity().finishAffinity();
                })
                .setNegativeButton(getString(R.string.action_cancel), (dialog, which) -> dialog.dismiss())
                .setCancelable(true)
                .show();
    }

    @NonNull
    @Override
    public HomeFragmentBinding getViewBinding() {
        return HomeFragmentBinding.inflate(getLayoutInflater());
    }
}

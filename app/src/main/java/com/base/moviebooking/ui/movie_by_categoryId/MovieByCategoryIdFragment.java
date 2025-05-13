package com.base.moviebooking.ui.movie_by_categoryId;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.base.moviebooking.DataLocalManager;
import com.base.moviebooking.R;
import com.base.moviebooking.adapter.HomeAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.MovieByCategoryFragmentBinding;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.ui.main.MainActivity;
import com.base.moviebooking.ui.show_time.ShowTimeFragment;
import com.base.moviebooking.ui.sign_in.SignInFragment;

import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MovieByCategoryIdFragment extends BaseFragment<MovieByCategoryFragmentBinding> {
    Category category;
    private MovieByCategoryIdModel mViewModel;
    private HomeAdapter homeAdapter;

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public boolean backPressed() {
        mViewController.backFromAddFragment(null);
        getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        return false;
    }

    @Override
    public void initView() {
        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
        getActivity().findViewById(R.id.ln_no_schedule).setVisibility(View.GONE);
        mViewModel = new ViewModelProvider(this).get(MovieByCategoryIdModel.class);
        //phim home
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rcvSearch2.setLayoutManager(gridLayoutManager);
        Bundle bundle = getArguments();
        Log.d("cather", "detail");

        if (bundle != null && !bundle.isEmpty()) {
            category = (Category) bundle.getSerializable("category");
            mViewModel.getMovieDataByCategoryId(category.getId());
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
//                    getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
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
                    if (movieListResponse.size() != 0) {
                        homeAdapter.addModels(movieListResponse, false);
                        Log.d("fat", "add Model", null);
                        getActivity().findViewById(R.id.dialog_load_2).setVisibility(View.GONE);
                        getActivity().findViewById(R.id.ln_no_schedule).setVisibility(View.GONE);
                    } else {
                        getActivity().findViewById(R.id.dialog_load_2).setVisibility(View.GONE);
                        getActivity().findViewById(R.id.ln_no_schedule).setVisibility(View.VISIBLE);
                    }
                }
            });
            binding.rcvSearch2.setAdapter(homeAdapter);
//        binding.categoryType.setText(category.getName().toString());
            TextView t = getActivity().findViewById(R.id.tvt_headerphim);
            t.setText(category.getName().toString());

        } else {
            // Không có dữ liệu để xử lý
            Log.d("cather", "des NO data");
        }

    }

    @Override
    public void initData() {
        ImageView back_btn = getActivity().findViewById(R.id.img_headerphim);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("back", "aaaa");
                System.out.println("hi");
                mViewController.backFromAddFragment(null);
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);

            }
        });
    }

    ;

    @NonNull
    @Override
    public MovieByCategoryFragmentBinding getViewBinding() {
        return MovieByCategoryFragmentBinding.inflate(getLayoutInflater());
    }


}

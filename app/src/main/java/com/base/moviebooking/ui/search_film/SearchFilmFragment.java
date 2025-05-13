package com.base.moviebooking.ui.search_film;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.base.moviebooking.DataLocalManager;
import com.base.moviebooking.R;
import com.base.moviebooking.adapter.HomeAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ActiveSearchFragmentBinding;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.main.MainActivity;
import com.base.moviebooking.ui.show_time.ShowTimeFragment;
import com.base.moviebooking.ui.sign_in.SignInFragment;

import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFilmFragment extends BaseFragment<ActiveSearchFragmentBinding> {
    private HomeAdapter homeAdapter;
    private SearchFilmModel mViewModel;
    private List<Movie> mListMovies;

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        mViewController.replaceFragment(HomeFragment.class, null);
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mViewModel = new ViewModelProvider(this).get(SearchFilmModel.class);
        binding.lnNoMovie.setVisibility(View.GONE);
        getActivity().findViewById(R.id.dialog_load_movie).setVisibility(View.GONE);
        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                mViewController.replaceFragment(HomeFragment.class, null);
            }
        });
        binding.imageDelete.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtKeyword.setText("");
            }
        }));
        binding.edtKeyword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMovie(binding.edtKeyword.getText().toString().trim());
                return true;
            }
            return false;
        });
        binding.edtKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().length() > 0) {
                    binding.imageDelete.setVisibility(View.VISIBLE);
                } else {
                    binding.imageDelete.setVisibility(View.GONE);
                    searchMovie(binding.edtKeyword.getText().toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void searchMovie(String keyWord) {
        // Kiểm tra xem adapter có dữ liệu hay không
        if (homeAdapter != null && homeAdapter.getItemCount() > 0) {
            // Clear dữ liệu hiện có và hiển thị dialog load movie
            homeAdapter.clear();
            homeAdapter.notifyDataSetChanged();
            getActivity().findViewById(R.id.dialog_load_movie).setVisibility(View.VISIBLE);
        } else {
            // Nếu adapter chưa có dữ liệu, tạo mới adapter và thiết lập RecyclerView
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
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            binding.rcvData.setLayoutManager(gridLayoutManager);
            binding.rcvData.setAdapter(homeAdapter);
        }

        mViewModel.getMovieDataByName(keyWord);

        mViewModel.dataMovie.observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieListResponse) {
                if (movieListResponse.size() != 0) {
                    // Cập nhật dữ liệu mới vào adapter
                    homeAdapter.clear();
                    homeAdapter.addModels(movieListResponse, false);
                    getActivity().findViewById(R.id.dialog_load_movie).setVisibility(View.GONE);
                    getActivity().findViewById(R.id.ln_no_movie).setVisibility(View.GONE);
                } else {
                    // Xử lý khi không có kết quả trả về
                    homeAdapter.clear();
                    getActivity().findViewById(R.id.dialog_load_movie).setVisibility(View.GONE);
                    getActivity().findViewById(R.id.ln_no_movie).setVisibility(View.VISIBLE);
                }
                homeAdapter.notifyDataSetChanged();
            }
        });
    }


    @NonNull
    @Override
    public ActiveSearchFragmentBinding getViewBinding() {
        return ActiveSearchFragmentBinding.inflate(getLayoutInflater());
    }
}

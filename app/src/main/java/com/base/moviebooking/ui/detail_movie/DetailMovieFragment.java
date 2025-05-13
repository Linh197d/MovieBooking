package com.base.moviebooking.ui.detail_movie;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.ActorAdapter;
import com.base.moviebooking.adapter.CategoryListAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.ThongtinFragmentBinding;
import com.base.moviebooking.entity.Actor;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.ui.show_time.ShowTimeViewModel;
import com.base.moviebooking.utils.StringUtil;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailMovieFragment extends BaseFragment<ThongtinFragmentBinding> {

    DetailMovieViewModel mViewModel;
    ShowTimeViewModel showTimeViewModel;
    private CategoryListAdapter categoryListAdapter;
    private ActorAdapter actorAdapter;
    private SimpleExoPlayer mPlayer;


    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        mViewModel = new ViewModelProvider(this).get(DetailMovieViewModel.class);
//        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailMovieViewModel.class);
        showTimeViewModel = new ViewModelProvider(requireParentFragment()).get(ShowTimeViewModel.class);
        showTimeViewModel.getData().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie s) {
                Log.d("transfer", "sendata observable");
                binding.descriptionMovie.setText(s.getDescription());
                binding.directorMovie.setText(s.getDirector());

                if (s.getImage() != null && !s.getImage().isEmpty()) {
                    Glide.with(requireContext())
                            .load(s.getImageDirector())
                            .into(binding.directorImage);
                } else {
                    // Nếu không có đường dẫn ảnh thì có thể hiển thị ảnh mặc định hoặc ẩn ImageView
                    binding.directorImage.setImageResource(R.drawable.dv5);
                    // hoặc
                    // binding.actorImage.setVisibility(View.GONE);
                }
//                        binding.descriptionMovie.setText(s.getTime());
//                        binding.descriptionMovie.setText(s.getTimeRelease());
                showCategory(s.getId());
                showActor(s.getId());
                if (!StringUtil.isEmpty(s.getTrailer())) {
                    Log.e("Movie Url", s.getTrailer());
                } else {
                }
            }
        });
    }

    @Override
    public void initData() {
        binding.imgPlayMovie.setOnClickListener(view -> startVideo());

    }

    public void showCategory(int movieId) {
        // Category
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.cateView.setLayoutManager(linearLayoutManager);
        mViewModel.getListCategoryByMovieId(movieId);
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
                    }

                    @Override
                    public void onChooseTime(Schedule schedule) {

                    }
                });

        mViewModel.dataCategory.observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categoriesListResponse) {
                categoryListAdapter.clear();
                categoryListAdapter.addModels(categoriesListResponse, false);
                Log.d("fat", "add Model Category", null);
                View dialogLoadSchedule = getActivity().findViewById(R.id.category_load);
                if (dialogLoadSchedule != null) {
                    dialogLoadSchedule.setVisibility(View.GONE);
                }
//                getActivity().findViewById(R.id.category_load).setVisibility(View.GONE);
            }
        });
        binding.cateView.setAdapter(categoryListAdapter);
    }

    //  Show actor
    public void showActor(int movieId) {
        // Actor
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.listActor.setLayoutManager(linearLayoutManager2);
        mViewModel.getListActorsByMovieId(movieId);
        actorAdapter = new ActorAdapter(getContext(), false, getContext(),
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
                    }

                    @Override
                    public void onChooseTime(Schedule schedule) {

                    }
                });

        mViewModel.dataActors.observe(getViewLifecycleOwner(), new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actorsListResponse) {
                actorAdapter.clear();
                if (actorsListResponse.size() > 0) {
                    actorAdapter.addModels(actorsListResponse, false);
                    Log.d("fat", "add Model Actor", null);
                    View dialogLoadSchedule = getActivity().findViewById(R.id.actor_load);
                    if (dialogLoadSchedule != null) {
                        dialogLoadSchedule.setVisibility(View.GONE);
                    }
//                    getActivity().findViewById(R.id.actor_load).setVisibility(View.GONE);
                } else {
                    actorsListResponse.add(new Actor(1, "Perdo Pascal", "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/494807_v9_bd.jpg"));
                    actorsListResponse.add(new Actor(2, "Anna Torv", "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/494807_v9_bd.jpg"));
                    actorsListResponse.add(new Actor(3, "Nico Parker", "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/494807_v9_bd.jpg"));
                    actorsListResponse.add(new Actor(4, "Nick Offerman", "https://resizing.flixster.com/-XZAfHZM39UwaGJIFWKAE8fS0ak=/v3/t/assets/494807_v9_bd.jpg"));
                    actorAdapter.addModels(actorsListResponse, false);
                    Log.d("fat", "add Model Actor", null);
                    View dialogLoadSchedule = getActivity().findViewById(R.id.actor_load);
                    if (dialogLoadSchedule != null) {
                        dialogLoadSchedule.setVisibility(View.GONE);
                    }
                }

            }
        });
        binding.listActor.setAdapter(actorAdapter);
    }

    private void initExoPlayer(String trailerUrl) {
        PlayerView mExoPlayerView = binding.exoplayer;

        if (mPlayer != null) {
            return;
        }


    }

    // Phương thức này sẽ được gọi từ ViewModel để tạo và khởi tạo ExoPlayer


    // Phương thức này sẽ được gọi từ ViewModel để release ExoPlayer khi không cần thiết nữa
    public void releaseExoPlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    private void startVideo() {
        binding.imgPlayMovie.setVisibility(View.GONE);
        if (mPlayer != null) {
            // Prepare video source
            mPlayer.prepare();
            // Set play video
            mPlayer.setPlayWhenReady(true);
        }
    }

    @NonNull
    @Override
    public ThongtinFragmentBinding getViewBinding() {
        return ThongtinFragmentBinding.inflate(getLayoutInflater());
    }
}

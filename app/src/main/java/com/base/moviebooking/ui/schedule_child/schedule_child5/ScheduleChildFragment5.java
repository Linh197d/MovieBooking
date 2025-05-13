package com.base.moviebooking.ui.schedule_child.schedule_child5;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.ScheduleAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.SchedulleChildFragment4Binding;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.MovieSchedule;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.listener.OnChooseRecyclerView;
import com.base.moviebooking.ui.schedule.ScheduleCinemaModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ScheduleChildFragment5 extends BaseFragment<SchedulleChildFragment4Binding> {
    ScheduleChildModel5 mViewModel;
    ScheduleCinemaModel scheduleCinemaModel;
    ScheduleAdapter scheduleAdapter;


    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        scheduleCinemaModel = new ViewModelProvider(this).get(ScheduleCinemaModel.class);
        mViewModel = new ViewModelProvider(this).get(ScheduleChildModel5.class);
        binding.rcvLichphim.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        String day = scheduleCinemaModel.day4.getValue().toString();
        Theater theater = scheduleCinemaModel.getDataTheater().getValue();
        mViewModel.getMovieHasSchedule(theater.getId(), day);
        scheduleAdapter = new ScheduleAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
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
            public void onChooseLichChieu(Schedule showTime) {

            }

            @Override
            public void onChooseCategory(Category category) {

            }

            @Override
            public void onChooseTime(Schedule schedule) {

            }
        }, this, scheduleCinemaModel);
        mViewModel.dataMovie.observe(getViewLifecycleOwner(), new Observer<List<MovieSchedule>>() {
            @Override
            public void onChanged(List<MovieSchedule> listMovieResponse) {
                if (listMovieResponse.size() != 0) {
                    scheduleAdapter.addModels(listMovieResponse, false);
                    Log.d("fat", "add Model", null);
                    View dialogLoadSchedule = getActivity().findViewById(R.id.dialog_load_schedule4);
                    if (dialogLoadSchedule != null) {
                        dialogLoadSchedule.setVisibility(View.GONE);
                    }
//                    getActivity().findViewById(R.id.dialog_load_schedule4).setVisibility(View.GONE);
                    binding.lnNoMovie.setVisibility(View.GONE);
                } else {
                    View dialogLoadSchedule = getActivity().findViewById(R.id.dialog_load_schedule4);
                    if (dialogLoadSchedule != null) {
                        dialogLoadSchedule.setVisibility(View.GONE);
                    }
                    binding.lnNoMovie.setVisibility(View.VISIBLE);
                }

            }
        });
        binding.rcvLichphim.setAdapter(scheduleAdapter);
        scheduleAdapter.notifyDataSetChanged();
    }

    @Override
    public void initData() {

    }

    @NonNull
    @Override
    public SchedulleChildFragment4Binding getViewBinding() {
        return SchedulleChildFragment4Binding.inflate(getLayoutInflater());
    }
}

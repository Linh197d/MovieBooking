package com.base.moviebooking.ui.schedule_child.schedule_child1;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.MovieSchedule;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class ScheduleChildModel1 extends ViewModel {
    public MutableLiveData<List<Schedule>> dataSchedule = new MutableLiveData<>();
    MutableLiveData<List<MovieSchedule>> dataMovie = new MutableLiveData<>();
    private Repository repository;

    @Inject
    public ScheduleChildModel1(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<MovieSchedule>> getDataMovie() {
        return dataMovie;
    }

    public MutableLiveData<List<Schedule>> getDataSchedule() {
        return dataSchedule;
    }

    public void getMovieHasSchedule(int theaterId, String day) {
        repository.getMoviesHasSchedule(theaterId, day)
                .subscribe(new SingleObserver<List<MovieSchedule>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<MovieSchedule> list) {
                        dataMovie.postValue(list);
                        Log.d("fat", "success Cinemas");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("token", "" + e.getMessage());
                    }
                });
    }

    public void getTimeSchedule(int theaterId, int movieId, String day) {
        repository.getTimeSchedule(theaterId, movieId, day)
                .subscribe(new SingleObserver<List<Schedule>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Schedule> list) {
                        dataSchedule.postValue(list);
                        Log.d("fat", "success Cinemas");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("token", "" + e.getMessage());
                    }
                });
    }
}

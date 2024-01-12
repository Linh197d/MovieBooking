package com.base.moviebooking.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.moviebooking.base.BaseViewModel;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends BaseViewModel {
    private final Repository repository;
    MutableLiveData<List<Movie>> dataMovie = new MutableLiveData<>();

    public MutableLiveData<List<Movie>> getDataMovie() {
        return dataMovie;
    }

    @Inject
    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getData() {
        repository.getMovieData()
                .subscribe(new SingleObserver<List<Movie>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Movie> movieListResponse) {
                        dataMovie.postValue(movieListResponse);
                        Log.d("fat", "successHomeViewMOdell");
                    }


                    @Override
                    public void onError(Throwable e) {


                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }


}




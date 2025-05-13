package com.base.moviebooking.ui.movie_by_categoryId;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class MovieByCategoryIdModel extends ViewModel {
    private final Repository repository;
    MutableLiveData<List<Movie>> dataMovie = new MutableLiveData<>();

    @Inject
    public MovieByCategoryIdModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Movie>> getDataMovie() {
        return dataMovie;
    }

    public void getMovieDataByCategoryId(int categoryId) {
        repository.getMovieDataByCategoryId(categoryId)
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

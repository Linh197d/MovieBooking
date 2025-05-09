package com.base.moviebooking.ui.home;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Category;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final Repository repository;
    MutableLiveData<List<Movie>> dataMovie = new MutableLiveData<>();
    MutableLiveData<List<Category>> dataCategory = new MutableLiveData<>();

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

    public void getListCategory(){
        repository.getListCategory()
                .subscribe(new SingleObserver<List<Category>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Category> categoriesListResponse) {
                        dataCategory.postValue(categoriesListResponse);
                        Log.d("fat", "success get Data Category");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });

    }

}




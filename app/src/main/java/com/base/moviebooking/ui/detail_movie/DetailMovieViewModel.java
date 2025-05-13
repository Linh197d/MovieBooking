package com.base.moviebooking.ui.detail_movie;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Actor;
import com.base.moviebooking.entity.Category;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class DetailMovieViewModel extends ViewModel {
    MutableLiveData<List<Category>> dataCategory = new MutableLiveData<>();
    MutableLiveData<List<Actor>> dataActors = new MutableLiveData<>();
    private Repository repository;

    @Inject
    public DetailMovieViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Category>> getDataCategory() {
        return dataCategory;
    }

    public MutableLiveData<List<Actor>> getDataActors() {
        return dataActors;
    }

    public void getListCategoryByMovieId(int movieId) {
        repository.getListCategoryByMovieId(movieId)
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

    public void getListActorsByMovieId(int movieId) {
        repository.getListActorByMovieId(movieId)
                .subscribe(new SingleObserver<List<Actor>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Actor> actorsListResponse) {
                        dataActors.postValue(actorsListResponse);
                        Log.d("fat", "success get Data Actor");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });

    }
}
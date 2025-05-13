package com.base.moviebooking.ui.theater;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class TheaterViewModel extends ViewModel {
    MutableLiveData<List<Theater>> dataCinema = new MutableLiveData<>();
    private Repository repository;

    @Inject
    public TheaterViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getCinema() {
        repository.getCinemas()
                .subscribe(new SingleObserver<List<Theater>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Theater> list) {
                        dataCinema.postValue(list);
                        Log.d("fat", "success Register");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

}
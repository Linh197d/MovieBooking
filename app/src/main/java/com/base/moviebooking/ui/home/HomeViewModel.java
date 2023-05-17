package com.base.moviebooking.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.moviebooking.base.BaseViewModel;
import com.base.moviebooking.base.ListResponse;
import com.base.moviebooking.network.repository.Repository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends BaseViewModel {
    private Repository repository;

    @Inject
    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }
}




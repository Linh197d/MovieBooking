package com.base.moviebooking.network.repository;

import com.base.moviebooking.base.ListResponse;
import com.base.moviebooking.base.ListResponseChild;
import com.base.moviebooking.base.ObjectResponse;
import com.base.moviebooking.network.ApiInterface;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private final ApiInterface apiInterface;

    @Inject
    Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }


}

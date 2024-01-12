package com.base.moviebooking.ui.show_time_child;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.moviebooking.base.BaseViewModel;
import com.base.moviebooking.entity.Cinema;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ShowTimeChildViewModel extends BaseViewModel {
    private Repository repository;
    MutableLiveData<List<Cinema>> data = new MutableLiveData<>();
    MutableLiveData<List<Schedule>> listSchedule = new MutableLiveData<>();

    public MutableLiveData<List<Cinema>> getData() {
        return data;
    }
    @Inject
    public ShowTimeChildViewModel(Repository repository) {
        this.repository = repository;
    }


    public void getCinemas( ) {
        repository.getCinemas()
                .subscribe(new SingleObserver<List<Cinema>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Cinema> list) {
                        data.postValue(list);
                        Log.d("fat", "success Cinemas");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("token", "" + e.getMessage());
                    }
                });
    }

    public void getListSchedule(int cinemaId,String day,int movieId ){
        repository.getschedules(cinemaId,day,movieId)
                .subscribe(new SingleObserver<List<Schedule>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Schedule> schedules) {
                    listSchedule.postValue(schedules);
                    Log.d("fat","success schedules viewmodel");
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }


}
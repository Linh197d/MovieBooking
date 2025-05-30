package com.base.moviebooking.ui.show_time_child;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Cinema;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class ShowTimeChildViewModel extends ViewModel {
    private Repository repository;
    MutableLiveData<List<Cinema>> data = new MutableLiveData<>();
    MutableLiveData<List<Schedule>> listSchedule = new MutableLiveData<>();

    @Inject
    public ShowTimeChildViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Cinema>> getData() {
        return data;
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
                        Log.d("fat", "" + e.getMessage());
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
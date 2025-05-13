package com.base.moviebooking.ui.schedule;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Theater;
import com.base.moviebooking.network.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ScheduleCinemaModel extends ViewModel {
    private final Repository repository;

    public MutableLiveData<Theater> dataTheater = new MutableLiveData<>();
    public MutableLiveData<String> day = new MutableLiveData<>();
    public MutableLiveData<String> day1 = new MutableLiveData<>();
    public MutableLiveData<String> day2 = new MutableLiveData<>();
    public MutableLiveData<String> day3 = new MutableLiveData<>();
    public MutableLiveData<String> day4 = new MutableLiveData<>();
    public MutableLiveData<String> day5 = new MutableLiveData<>();
    public MutableLiveData<String> day6 = new MutableLiveData<>();

    @Inject
    public ScheduleCinemaModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<Theater> getDataTheater() {
        return dataTheater;
    }

    public MutableLiveData<String> getDay() {
        return day;
    }

    public MutableLiveData<String> getDay1() {
        return day1;
    }

    public MutableLiveData<String> getDay2() {
        return day2;
    }

    public MutableLiveData<String> getDay3() {
        return day3;
    }

    public MutableLiveData<String> getDay4() {
        return day4;
    }

    public MutableLiveData<String> getDay5() {
        return day5;
    }

    public MutableLiveData<String> getDay6() {
        return day6;
    }

    public void sendDataTheater(Theater theater) {
        dataTheater.setValue(theater);
    }

    public void sendDay(String dayString) {
        day.setValue(dayString);
    }

    public void sendDay1(String dayString) {
        day1.setValue(dayString);
    }

    public void sendDay2(String dayString) {
        day2.setValue(dayString);
    }

    public void sendDay3(String dayString) {
        day3.setValue(dayString);
    }

    public void sendDay4(String dayString) {
        day4.setValue(dayString);
    }

    public void sendDay5(String dayString) {
        day5.setValue(dayString);
    }

    public void sendDay6(String dayString) {
        day6.setValue(dayString);
    }


}

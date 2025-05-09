package com.base.moviebooking.ui.show_time;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Movie;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel

public class ShowTimeViewModel extends ViewModel {
    public  MutableLiveData<Movie> dataMovie = new MutableLiveData<>();
    public MutableLiveData<Movie> getData() {
        return dataMovie;
    }

    @Inject
    ShowTimeViewModel() {
    }
    public void sendData(Movie movie){
        dataMovie.setValue(movie);
    }
}
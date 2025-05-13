package com.base.moviebooking.ui.show_time;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Movie;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ShowTimeViewModel extends ViewModel {
    public MutableLiveData<Movie> dataMovie = new MutableLiveData<>();
    public MutableLiveData<Movie> dataMovieComment = new MutableLiveData<>();

    @Inject
    ShowTimeViewModel() {
    }

    public MutableLiveData<Movie> getData() {
        return dataMovie;
    }

    public MutableLiveData<Movie> getDataMovieComent() {
        return dataMovieComment;
    }

    public void sendData(Movie movie) {
        dataMovie.setValue(movie);

    }

    public void sendDataMovieComment(Movie movie) {
        dataMovieComment.setValue(movie);
    }
}

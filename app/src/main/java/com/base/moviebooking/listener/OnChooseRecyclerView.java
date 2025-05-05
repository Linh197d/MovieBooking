package com.base.moviebooking.listener;

import com.base.moviebooking.entity.FilmInfo;
import com.base.moviebooking.entity.Schedule;
import com.base.moviebooking.entity.Movie;
import com.base.moviebooking.entity.Theater;

public interface OnChooseRecyclerView {
    void onChoosePhim(Movie movie);
    void onChooseRap(Theater theater);
    void onChooseFilmInfo(FilmInfo filmInfo);
    void onChooseLichChieu(Schedule schedule);
}



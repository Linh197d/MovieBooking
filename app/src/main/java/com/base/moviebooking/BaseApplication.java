package com.base.moviebooking;

import android.app.Activity;
import android.app.Application;


import javax.inject.Inject;


import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}

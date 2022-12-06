package com.base.mvvmbaseproject.di.module;


import com.base.mvvmbaseproject.ui.UserProfile.UserFragment;
import com.base.mvvmbaseproject.ui.countnotify.CountNotifyFragment;
import com.base.mvvmbaseproject.ui.home.HomeFragment;
import com.base.mvvmbaseproject.ui.login.LoginFragment;
import com.base.mvvmbaseproject.ui.splash.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {

    //TODO bind fragment
    @ContributesAndroidInjector
    abstract SplashFragment bindSplashFragment();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract CountNotifyFragment bindCountNotifyFragment();

    @ContributesAndroidInjector
    abstract UserFragment bindUserFragment();
}

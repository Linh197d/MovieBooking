package com.base.mvvmbaseproject.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.base.mvvmbaseproject.di.ViewModelFactory;
import com.base.mvvmbaseproject.ui.UserProfile.UserViewModel;
import com.base.mvvmbaseproject.ui.countnotify.CountNotifyViewModel;
import com.base.mvvmbaseproject.ui.home.HomeViewModel;
import com.base.mvvmbaseproject.ui.login.LoginViewModel;
import com.base.mvvmbaseproject.ui.lskhambenh.LSKhamBenhFragment;
import com.base.mvvmbaseproject.ui.lskhambenh.LSkhamBenhViewModel;
import com.base.mvvmbaseproject.ui.main.MainViewModel;
import com.base.mvvmbaseproject.ui.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    //bind ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CountNotifyViewModel.class)
    abstract ViewModel bindCountNotifyViewModel(CountNotifyViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel.class)
    abstract ViewModel bindUserViewModel(UserViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LSkhamBenhViewModel.class)
    abstract ViewModel bindLSKBViewModel(LSkhamBenhViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}

package com.base.moviebooking.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.base.moviebooking.di.ViewModelFactory;
import com.base.moviebooking.ui.chonghe.ChonGheViewModel;
import com.base.moviebooking.ui.dangky.DangKyViewModel;
import com.base.moviebooking.ui.dangnhap.DangNhapViewModel;
import com.base.moviebooking.ui.dienanh.DienAnhViewModel;
import com.base.moviebooking.ui.home.HomeViewModel;
import com.base.moviebooking.ui.main.MainViewModel;
import com.base.moviebooking.ui.rapphim.RapViewModel;
import com.base.moviebooking.ui.splash.SplashViewModel;
import com.base.moviebooking.ui.taikhoan.TaiKhoanViewModel;

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
    @ViewModelKey(TaiKhoanViewModel.class)
    abstract ViewModel bindTaiKhoanViewModel(TaiKhoanViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DienAnhViewModel.class)
    abstract ViewModel bindDienAnhViewModel(DienAnhViewModel viewModel);


    @Binds
    @IntoMap
    @ViewModelKey(RapViewModel.class)
    abstract ViewModel bindRapViewModel(RapViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DangKyViewModel.class)
    abstract ViewModel bindDangKyViewModel(DangKyViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DangNhapViewModel.class)
    abstract ViewModel bindDangNhapViewModel(DangNhapViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChonGheViewModel.class)
    abstract ViewModel bindChonGheViewModel(ChonGheViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);



    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}

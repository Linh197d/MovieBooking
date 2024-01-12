package com.base.moviebooking.di.module;


import com.base.moviebooking.ui.change_pass.ChangePassFragment;
import com.base.moviebooking.ui.chonghe.ChonGheFragment;
import com.base.moviebooking.ui.film_info.FilmInfoChildFragment;
import com.base.moviebooking.ui.giaodich.GiaoDichFragment;
import com.base.moviebooking.ui.show_time.ShowTimeFragment;
import com.base.moviebooking.ui.account.AccountFragment;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.show_time_child.ShowTimeChildFragment;
import com.base.moviebooking.ui.sign_in.SignInFragment;
import com.base.moviebooking.ui.sign_up.SignUpFragment;
import com.base.moviebooking.ui.splash.SplashFragment;
import com.base.moviebooking.ui.thanhtoan.ThanhToanFragment;
import com.base.moviebooking.ui.theater.TheaterFragment;
import com.base.moviebooking.ui.film_info.FilmInfoFragment;
import com.base.moviebooking.ui.detail_movie.DetailMovieFragment;
import com.base.moviebooking.ui.thongtin_Thanhtoan.ThongTinThanhToanFragment;
import com.base.moviebooking.ui.user_info.UserInfoFragment;

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
    abstract AccountFragment bindAccountFragment();

    @ContributesAndroidInjector
    abstract TheaterFragment bindTheaterFragment();

    @ContributesAndroidInjector
    abstract FilmInfoFragment bindFilmInfoFragment();

    @ContributesAndroidInjector
    abstract FilmInfoChildFragment bindFilmInfoChildFragment();

    @ContributesAndroidInjector
    abstract SignUpFragment bindSignUpFragment();

    @ContributesAndroidInjector
    abstract SignInFragment bindSignInFragment();

    @ContributesAndroidInjector
    abstract ChonGheFragment bindChonGheFragment();

    @ContributesAndroidInjector
    abstract ShowTimeFragment bindShowTimeFragment();

    @ContributesAndroidInjector
    abstract ShowTimeChildFragment bindShowTimeChildFragment();

    @ContributesAndroidInjector
    abstract DetailMovieFragment bindDetailMovieFragment();

    @ContributesAndroidInjector
    abstract ThanhToanFragment bindThanhToanFragment();

@ContributesAndroidInjector
    abstract ChangePassFragment bindChangePassFragment();

    @ContributesAndroidInjector
    abstract ThongTinThanhToanFragment bindThongTinThanhToanFragment();

    @ContributesAndroidInjector
    abstract GiaoDichFragment bindGiaoDichFragment();

    @ContributesAndroidInjector
    abstract UserInfoFragment bindUserInfoFragment();


}

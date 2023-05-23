package com.base.moviebooking.di.module;


import com.base.moviebooking.ui.chonghe.ChonGheFragment;
import com.base.moviebooking.ui.dangky.DangKyFragment;
import com.base.moviebooking.ui.dangnhap.DangNhapFragment;
import com.base.moviebooking.ui.dienanh.DienAnhFragment;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.lichchieu.LichChieuFragment;
import com.base.moviebooking.ui.lichphim.LichPhimFragment;
import com.base.moviebooking.ui.rapphim.RapFragment;
import com.base.moviebooking.ui.splash.SplashFragment;
import com.base.moviebooking.ui.taikhoan.TaiKhoanFragment;
import com.base.moviebooking.ui.thongtinphim.ThongTinPhimFragment;

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
    abstract TaiKhoanFragment bindTaiKhoanFragment();

    @ContributesAndroidInjector
    abstract RapFragment bindRapFragment();

    @ContributesAndroidInjector
    abstract DienAnhFragment bindDienAnhFragment();

    @ContributesAndroidInjector
    abstract DangKyFragment bindDangKyFragment();

    @ContributesAndroidInjector
    abstract DangNhapFragment bindDangNhapFragment();

    @ContributesAndroidInjector
    abstract ChonGheFragment bindChonGheFragment();

    @ContributesAndroidInjector
    abstract LichPhimFragment bindLichPhimFragment();

    @ContributesAndroidInjector
    abstract LichChieuFragment bindLichChieuFragment();

    @ContributesAndroidInjector
    abstract ThongTinPhimFragment bindThongTinPhimFragment();


}

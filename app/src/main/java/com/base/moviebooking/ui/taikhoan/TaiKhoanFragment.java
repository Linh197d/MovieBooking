package com.base.moviebooking.ui.taikhoan;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;


import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.DangkyFragmentBinding;
import com.base.moviebooking.databinding.TaikhoanFragmentBinding;
import com.base.moviebooking.ui.dangky.DangKyFragment;
import com.base.moviebooking.ui.dangnhap.DangNhapFragment;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.splash.SplashViewModel;

public class TaiKhoanFragment extends BaseFragment<TaikhoanFragmentBinding> {
    String TAG = "fat";
    private TaiKhoanViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.taikhoan_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return true;
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.taikhoan_fragment, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.tvt_header);
//        textView.setText("Tài khoản");
//        return rootView;
//    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(TaiKhoanViewModel.class);
        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(DangKyFragment.class,null);
            }
        });
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(DangNhapFragment.class,null);
            }
        });
    }

    @Override
    public void initData() {

    }


}


package com.base.moviebooking.ui.dienanh;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.DienanhFragmentBinding;
import com.base.moviebooking.databinding.SplashFragmentBinding;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.splash.SplashViewModel;
import com.google.android.material.tabs.TabLayout;

public class DienAnhFragment extends BaseFragment<DienanhFragmentBinding> {
    private DienAnhViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.dienanh_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }



    @Override
    public boolean backPressed() {
        return true;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(DienAnhViewModel.class);

    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.dienanh_fragment, container, false);
//        TextView textView = (TextView) rootView.findViewById(R.id.tvt_header);
//        textView.setText("Điện Ảnh");
//        ImageView imageView = (ImageView) rootView.findViewById(R.id.img_header);
//        imageView.setImageResource(R.drawable.movieglass);
//
//        return rootView;
//    }

    @Override
    public void onResume() {
        ImageView imageView= getActivity().findViewById(R.id.img_header);
        imageView.setImageResource(R.drawable.back);
        TextView textView = getActivity().findViewById(R.id.tvt_header);
        textView.setText("Điện ảnh");
        getActivity().findViewById(R.id.view_header).setVisibility(View.GONE);
        super.onResume();
    }

    @Override
    public void initData() {

    }


}

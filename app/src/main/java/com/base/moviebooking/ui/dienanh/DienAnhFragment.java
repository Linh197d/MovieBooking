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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.adapter.DienAnhAdapter;
import com.base.moviebooking.adapter.RapAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.DienanhFragmentBinding;
import com.base.moviebooking.databinding.SplashFragmentBinding;
import com.base.moviebooking.entity.DienAnh;
import com.base.moviebooking.entity.Phim;
import com.base.moviebooking.entity.Rap;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.ui.splash.SplashViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class DienAnhFragment extends BaseFragment<DienanhFragmentBinding> {
    private DienAnhViewModel mViewModel;
    private DienAnhAdapter dienAnhAdapter;
    private List<DienAnh> dienAnhList = new ArrayList<>();
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
        binding.rcvDienanh.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        dienAnhAdapter = new DienAnhAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
            @Override
            public void onChoosePhim(Phim phim) {

            }

            @Override
            public void onChooseRap(Rap rap) {

            }
        });
        dienAnhAdapter.addModels(getListModel(),false);
        binding.rcvDienanh.setAdapter(dienAnhAdapter);
    }
    private List<DienAnh> getListModel() {
        dienAnhList.add(new DienAnh("Preview: Guardians Of The Galaxy Vol.3 Lấy Lại Niềm Tin Dành Cho Dòng Phim Siêu Anh Hùng"
                ,R.drawable.antman,"116 Nguyễn Du,Quận 1,Tp.HCM"));
        dienAnhList.add(new DienAnh("Preview: Guardians Of The Galaxy Vol.3 Lấy Lại Niềm Tin Dành Cho Dòng Phim Siêu Anh Hùng"
                        ,R.drawable.antman,"116 Nguyễn Du,Quận 1,Tp.HCM"));
 dienAnhList.add(new DienAnh("Preview: Guardians Of The Galaxy Vol.3 Lấy Lại Niềm Tin Dành Cho Dòng Phim Siêu Anh Hùng"
                        ,R.drawable.antman,"116 Nguyễn Du,Quận 1,Tp.HCM"));
 dienAnhList.add(new DienAnh("Preview: Guardians Of The Galaxy Vol.3 Lấy Lại Niềm Tin Dành Cho Dòng Phim Siêu Anh Hùng"
                        ,R.drawable.antman,"116 Nguyễn Du,Quận 1,Tp.HCM"));
 dienAnhList.add(new DienAnh("Preview: Guardians Of The Galaxy Vol.3 Lấy Lại Niềm Tin Dành Cho Dòng Phim Siêu Anh Hùng"
                        ,R.drawable.antman,"116 Nguyễn Du,Quận 1,Tp.HCM"));

        return dienAnhList;
    }
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

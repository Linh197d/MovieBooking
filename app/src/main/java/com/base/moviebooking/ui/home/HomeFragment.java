package com.base.moviebooking.ui.home;

import android.os.Handler;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.base.moviebooking.OnChooseRecyclerView;
import com.base.moviebooking.R;
import com.base.moviebooking.adapter.HomeAdapter;
import com.base.moviebooking.adapter.SlideAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.HomeFragmentBinding;
import com.base.moviebooking.entity.LichChieu;
import com.base.moviebooking.entity.Phim;
import com.base.moviebooking.entity.Rap;
import com.base.moviebooking.entity.Slide;
import com.base.moviebooking.ui.chonghe.ChonGheFragment;
import com.base.moviebooking.ui.dienanh.DienAnhFragment;
import com.base.moviebooking.ui.lichphim.LichPhimFragment;
import com.base.moviebooking.ui.rapphim.RapFragment;
import com.base.moviebooking.ui.taikhoan.TaiKhoanFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeFragment extends BaseFragment<HomeFragmentBinding> {
    private List<Slide> list;
    private List<Phim> phimList= new ArrayList<>();
    private SlideAdapter slideAdapter;
    private HomeAdapter homeAdapter;
    private Handler mHandler= new Handler();
    private Runnable mRunnable= new Runnable() {
        @Override
        public void run() {
            if(binding.viewpager.getCurrentItem()== list.size()-1){
                binding.viewpager.setCurrentItem(0);
            }else{
                binding.viewpager.setCurrentItem(binding.viewpager.getCurrentItem()+1);
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment;
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
        getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        HomeViewModel mViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        //slide
        slideAdapter = new SlideAdapter(getContext(), getListSlide());
        binding.viewpager.setAdapter(slideAdapter);
        binding.circleIndicator.setViewPager(binding.viewpager);
        slideAdapter.registerDataSetObserver(binding.circleIndicator.getDataSetObserver());
        mHandler.postDelayed(mRunnable,5000);
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable,5000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //phim home
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        binding.rcvSearch.setLayoutManager(gridLayoutManager);

        homeAdapter = new HomeAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
            @Override
            public void onChoosePhim(Phim phim) {
                mViewController.addFragment(LichPhimFragment.class,null);

            }

            @Override
            public void onChooseRap(Rap rap) {

            }

            @Override
            public void onChooseLichChieu(LichChieu lichChieu) {

            }
        });
        homeAdapter.addModels(getListModel(),false);
        binding.rcvSearch.setAdapter(homeAdapter);
    }



    @Override
    public void initData() {



    }

    ;

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {

    }

    private List<Slide> getListSlide() {
         list = new ArrayList<>();
        list.add(new Slide(R.drawable.galaxy));
        list.add(new Slide(R.drawable.antman));
        list.add(new Slide(R.drawable.mario));
        list.add(new Slide(R.drawable.avatar));
        return list;
    }
    private List<Phim> getListModel() {
        phimList.add(new Phim("Vệ binh dải ngân hà 3",R.drawable.phim1,"15/5/2022","Kể về vụ đắm thuyền ngọt ngào"));
        phimList.add(new Phim("Titanic",R.drawable.phim2,"15/5/2022","Kể về vụ đắm thuyền ngọt ngào"));
        phimList.add(new Phim("Spiderman",R.drawable.phim1,"15/5/2022","Kể về vụ đắm thuyền ngọt ngào"));
        phimList.add(new Phim("Marvel",R.drawable.phim2,"15/5/2022","Kể về vụ đắm thuyền ngọt ngào"));
        phimList.add(new Phim("Mèo siêu quậy ở viện bảo tàng 2",R.drawable.phim1,"15/5/2022","Kể về vụ đắm thuyền ngọt ngào"));
        phimList.add(new Phim("Mèo siêu quậy ở viện bảo tàng 2",R.drawable.phim1,"15/5/2022","Kể về vụ đắm thuyền ngọt ngào"));
        return phimList;
    }
    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable,3000);
    }
}

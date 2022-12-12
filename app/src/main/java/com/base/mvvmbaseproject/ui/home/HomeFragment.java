package com.base.mvvmbaseproject.ui.home;

import android.annotation.SuppressLint;
import android.text.TextShaper;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.adapter.DataCKAdapter;
import com.base.mvvmbaseproject.adapter.SearchAdapter;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.base.ListResponse;
import com.base.mvvmbaseproject.databinding.HomeFragmentBinding;
import com.base.mvvmbaseproject.entity.DataServicesCK;
import com.base.mvvmbaseproject.entity.SearchResponse;
import com.base.mvvmbaseproject.ui.UserProfile.UserFragment;
import com.base.mvvmbaseproject.ui.countnotify.CountNotifyFragment;
import com.base.mvvmbaseproject.ui.lskhambenh.LSKhamBenhFragment;

import java.util.List;


public class HomeFragment extends BaseFragment<HomeFragmentBinding> {
    private HomeViewModel mViewModel;
    private SearchAdapter searchAdapter;
    private DataCKAdapter searchAdapterCK;

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
        //get du lieu ServiceDV
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel.class);
        searchAdapter = new SearchAdapter(getContext());
        mViewModel.getData(20, 1);
        mViewModel.dataService.observe(getViewLifecycleOwner(), new Observer<ListResponse<SearchResponse>>() {
            @Override
            public void onChanged(ListResponse<SearchResponse> searchResponseListResponse) {
                searchAdapter.refresh(searchResponseListResponse.getData());
            }
        });
        binding.rcvSearch.setAdapter(searchAdapter);
        //binding.rcvSearch.setOnLoadingMoreListener(() -> mViewModel.search(false));
        //binding.rcvSearch.setOnRefreshListener(() -> mViewModel.search(true));
        /*binding.rcvSearch.setOnItemClickListener((adapter, viewHolder, viewType, position) -> {
            SearchResponse searchResponse = searchAdapter.getItem(position,SearchResponse.class);
            //Toast.makeText(getContext(), searchResponse.getName()+"  "+searchResponse.getPrice(), Toast.LENGTH_SHORT).show();
        });*/
        //get dữ liệu ServiceCK
        searchAdapterCK = new DataCKAdapter(getContext());
        mViewModel.getDataCK(20, 10);
        mViewModel.dataServiceCK.observe(getViewLifecycleOwner(), new Observer<ListResponse<DataServicesCK>>() {
            @Override
            public void onChanged(ListResponse<DataServicesCK> searchResponseListResponse) {
                searchAdapterCK.refresh(searchResponseListResponse.getData());
            }
        });
    }

    @Override
    public void initData() {

        binding.btnCK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rcvSearch.setAdapter(searchAdapterCK);
                binding.rcvSearch.setBackgroundResource(R.drawable.custom_rcvck);

                // binding.rcvSearch.setBackgroundColor(5514100);

            }
        });
        binding.btnDv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.rcvSearch.setAdapter(searchAdapter);
                binding.rcvSearch.setBackgroundResource(R.drawable.custom_rcvdv);
                // binding.rcvSearch.setBackgroundColor(5514100);
            }
        });
        binding.btnalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(CountNotifyFragment.class, null);
            }
        });
        getActivity().findViewById(R.id.btnHoSo).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewController.addFragment(UserFragment.class, null);
            }
        });
        getActivity().findViewById(R.id.btnXetNghiem).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mViewController.addFragment(LSKhamBenhFragment.class, null);
            }
        });
    }

    ;

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        if (isRefresh) {
            searchAdapter.refresh(data);
        } else {
            searchAdapter.addModels(data, false);
        }
    }
}

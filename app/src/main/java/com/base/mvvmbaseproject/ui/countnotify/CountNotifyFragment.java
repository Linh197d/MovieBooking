package com.base.mvvmbaseproject.ui.countnotify;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.adapter.CountNotifyAdapter;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.databinding.CountNotifyFragmentBinding;
import com.base.mvvmbaseproject.entity.CountNotify;

import java.util.ArrayList;
//import com.base.mvvmbaseproject.databinding.;



public class CountNotifyFragment extends BaseFragment<CountNotifyFragmentBinding>{
    private CountNotifyAdapter notifyAdapter;
    private ArrayList<CountNotify> mCountNotify ;


    @Override
    protected int getLayoutId() {
        return R.layout.count_notify_fragment;
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
        mCountNotify = new ArrayList<>();
        initArray();
        notifyAdapter = new CountNotifyAdapter(getContext(),mCountNotify);
        CountNotifyViewModel mViewModel = ViewModelProviders.of(this, viewModelFactory).get(CountNotifyViewModel.class);
        mViewModel.getCountNotify();
        binding.rcvThongbao.setAdapter(notifyAdapter);
        binding.backCountnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert mViewController != null;
                mViewController.backFromAddFragment(null);
            }
        }
        );
        mViewModel.countNotify.observe(getViewLifecycleOwner(), new Observer<CountNotify>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(CountNotify countNotify) {

                notifyAdapter.notifyDataSetChanged();


            }
        });

    }
    public void initArray(){
        mCountNotify.add(new CountNotify(0,"1 ngày"));
        mCountNotify.add(new CountNotify(0,"1 ngày"));
        mCountNotify.add(new CountNotify(0,"2 ngày"));
        mCountNotify.add(new CountNotify(1,"3 ngày"));
        mCountNotify.add(new CountNotify(1,"6 ngày"));
        mCountNotify.add(new CountNotify(1,"6 ngày"));
        mCountNotify.add(new CountNotify(1,"8 ngày"));
        mCountNotify.add(new CountNotify(1,"10 ngày"));
        mCountNotify.add(new CountNotify(1,"12 ngày"));
        mCountNotify.add(new CountNotify(1,"12 ngày"));
    }


    @Override
    public void initData() {

    }


}

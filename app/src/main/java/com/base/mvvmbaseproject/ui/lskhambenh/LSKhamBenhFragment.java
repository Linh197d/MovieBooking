package com.base.mvvmbaseproject.ui.lskhambenh;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.mvvmbaseproject.OnChooseRecyclerView;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.adapter.LSKhamBenhAdapter;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.base.ListResponseChild;
import com.base.mvvmbaseproject.base.ObjectResponse;
import com.base.mvvmbaseproject.databinding.LskhambenhBinding;
import com.base.mvvmbaseproject.dialog.DialogCamera;
import com.base.mvvmbaseproject.entity.BloodType;
import com.base.mvvmbaseproject.entity.DeleteLSKB;
import com.base.mvvmbaseproject.entity.LSKhamBenh;
import com.base.mvvmbaseproject.ui.UserProfile.UserFragment;
import com.base.mvvmbaseproject.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class LSKhamBenhFragment extends BaseFragment<LskhambenhBinding> {
    private LSkhamBenhViewModel mViewModel;
    private LSKhamBenhAdapter lsKhamBenhAdapter;
    private ArrayList<LSKhamBenh> mListDeleteLSKB;


    @Override
    protected int getLayoutId() {
        return R.layout.lskhambenh;
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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LSkhamBenhViewModel.class);
        binding.rcvLskb.setListLayoutManager(LinearLayoutManager.VERTICAL);
        lsKhamBenhAdapter = new LSKhamBenhAdapter(getContext(), false, getContext(), new OnChooseRecyclerView() {
            @Override
            public void onChoose(BloodType bloodType) {
            }

            @Override
            public void onChooseLSKB(LSKhamBenh lsKhamBenh) {
                openDialodDeleteLSKB(lsKhamBenh.getId());
            }
        }, mListDeleteLSKB);
        mViewModel.dataDelete.observe(getViewLifecycleOwner(), new Observer<ObjectResponse<DeleteLSKB>>() {
            @Override
            public void onChanged(ObjectResponse<DeleteLSKB> deleteLSKBListResponse) {
                if (deleteLSKBListResponse.getStatus() == 200) {
//                    lsKhamBenhAdapter.clear();
//                    mViewModel.search(true);
                }
            }
        });
        binding.rcvLskb.setAdapter(lsKhamBenhAdapter);
        binding.rcvLskb.setOnLoadingMoreListener(() -> mViewModel.search(false));
        binding.rcvLskb.setOnRefreshListener(() ->
        {
            lsKhamBenhAdapter.clear();
            mViewModel.search(true);
        });
        binding.rcvLskb.setOnItemClickListener((adapter, viewHolder, viewType, position) -> {
            ListResponseChild searchResponse = lsKhamBenhAdapter.getItem(position, ListResponseChild.class);
        });
        mViewModel.search(true);
        mViewModel.getDataLSKB().observe(getViewLifecycleOwner(),
                searchResponseListResponse -> {
                    //mListDeleteLSKB.addAll(searchResponseListResponse.getData());
                    handleLoadMoreResponse(searchResponseListResponse, searchResponseListResponse.isRefresh(), searchResponseListResponse.isCanLoadmore()
                    );
                }
        );
        binding.rcvLskb.setAdapter(lsKhamBenhAdapter);
    }

    private void openDialodDeleteLSKB(int id) {
        DialogCamera dialog = new DialogCamera(getContext());
        dialog.setContentView(R.layout.layout_dialog_delete_lskb);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setCancelable(true);
        dialog.show();
        Button button_yes = dialog.findViewById(R.id.btn_yes);
        Button button_no = dialog.findViewById(R.id.btn_no);
        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.delete(id);
                lsKhamBenhAdapter.clear();
                new Handler().postDelayed(() -> {
                    mViewModel.search(true);
                }, 500);
                dialog.dismiss();
            }
        });
        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        if (isRefresh) {
            binding.rcvLskb.refresh(data);
        } else {
            binding.rcvLskb.addItem(data);
        }
        binding.rcvLskb.enableLoadmore(canLoadmore);
    }

    @Override
    public void initData() {
        getActivity().findViewById(R.id.btnTrangChu).setOnClickListener(view -> mViewController.addFragment(HomeFragment.class, null));
        getActivity().findViewById(R.id.btnHoSo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(UserFragment.class, null);
            }
        });
        getActivity().findViewById(R.id.backLSKB).setOnClickListener(view -> mViewController.backFromAddFragment(null));

    }
}

package com.base.moviebooking.ui.giaodich;

import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.GiaoDichAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.GiaodichFragmentBinding;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.listener.GiaoDichListener;

import java.util.List;

public class GiaoDichFragment extends BaseFragment<GiaodichFragmentBinding> {
    private GiaoDichAdapter giaoDichAdapter;
    private GiaoDichViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.giaodich_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(GiaoDichViewModel.class);
        mViewModel.getThongTinThanhToan();
        binding.rcvGd.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        giaoDichAdapter = new GiaoDichAdapter(getContext(), false, new GiaoDichListener() {
            @Override
            public void onChooseGD() {

            }
        });
        mViewModel.dataThanhtoan.observe(getViewLifecycleOwner(), new Observer<List<ThongTinThanhToan>>() {
            @Override
            public void onChanged(List<ThongTinThanhToan> list) {
                giaoDichAdapter.addModels(list, false);
            }
        });
        binding.rcvGd.setAdapter(giaoDichAdapter);

    }

    @Override
    public void initData() {
        getActivity().findViewById(R.id.img_headerphim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.backFromAddFragment(null);
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);

            }
        });
        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
        TextView t = getActivity().findViewById(R.id.tvt_headerphim);
        t.setText("Thông tin giao dịch");

    }

    @Override
    public void onResume() {
        TextView t = getActivity().findViewById(R.id.tvt_headerphim);
        t.setText("Thông tin giao dịch");
        super.onResume();
    }
}

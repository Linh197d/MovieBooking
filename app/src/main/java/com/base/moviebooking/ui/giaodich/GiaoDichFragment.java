package com.base.moviebooking.ui.giaodich;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.moviebooking.R;
import com.base.moviebooking.adapter.GiaoDichAdapter;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.GiaodichFragmentBinding;
import com.base.moviebooking.entity.CancelTicket;
import com.base.moviebooking.entity.LoginResponse;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.listener.GiaoDichListener;

import java.util.List;

public class GiaoDichFragment extends BaseFragment<GiaodichFragmentBinding> {
    private GiaoDichAdapter giaoDichAdapter;
    private GiaoDichViewModel mViewModel;
    private List<ThongTinThanhToan> listTT;
    private Dialog dialog;

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
        mViewModel = ViewModelProviders.of(this).get(GiaoDichViewModel.class);
        mViewModel.getThongTinThanhToan();
        binding.rcvGd.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        giaoDichAdapter = new GiaoDichAdapter(getContext(), false, new GiaoDichListener() {
            @Override
            public void onChooseGD(String code) {
                huyVe(code);
            }
        });

        mViewModel.dataThanhtoan.observe(getViewLifecycleOwner(), new Observer<List<ThongTinThanhToan>>() {
            @Override
            public void onChanged(List<ThongTinThanhToan> list) {
                giaoDichAdapter.clear();
                giaoDichAdapter.addModels(list, false);
                listTT = list;
            }
        });
        mViewModel.dataRespone.observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse response) {
                if (response.isSuccess()) {
                    Toast.makeText(getContext(), "Huỷ vé thành công", Toast.LENGTH_SHORT).show();
//                    giaoDichAdapter.notifyDataSetChanged();
                    mViewModel.getThongTinThanhToan();
                    dialog.dismiss();
                } else
                    Toast.makeText(getContext(), response.getData().getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
        binding.rcvGd.setAdapter(giaoDichAdapter);

    }

    private void huyVe(String code) {
        dialog = new Dialog(requireContext(), R.style.MyAlertDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_huy_ve);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.show();
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.huyVe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.huyVe(new CancelTicket(code));
            }
        });
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

    @NonNull
    @Override
    public GiaodichFragmentBinding getViewBinding() {
        return GiaodichFragmentBinding.inflate(getLayoutInflater());
    }
}

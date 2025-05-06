package com.base.moviebooking.ui.account;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.DataLocalManager;
import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.TaikhoanFragmentBinding;
import com.base.moviebooking.entity.Account;
import com.base.moviebooking.ui.giaodich.GiaoDichFragment;
import com.base.moviebooking.ui.sign_in.SignInFragment;
import com.base.moviebooking.ui.sign_up.SignUpFragment;
import com.base.moviebooking.ui.user_info.UserInfoFragment;

import java.util.List;


public class AccountFragment extends BaseFragment<TaikhoanFragmentBinding> {
    String TAG = "fat";
    private AccountViewModel mViewModel;
    Uri imageUser;

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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountViewModel.class);
        mViewModel.getInfo();
        mViewModel.dataUser.observe(getViewLifecycleOwner(), new Observer<List<Account>>() {
            @Override
            public void onChanged(List<Account> account) {
                Log.d("mmm", "dataUser" + account.get(0).getFullName());
                binding.tvtName.setText(account.get(0).getFullName());
                Log.d(TAG, "name" + account.get(0).getFullName());
            }
        });
    }

    @Override
    public void initData() {
        eventClick();
    }

    private void eventClick() {
        binding.btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(SignUpFragment.class, null);
            }
        });
        binding.btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(SignInFragment.class, null);
            }
        });
        binding.dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.lnUser.setVisibility(View.GONE);
                binding.lnNoUser.setVisibility(View.VISIBLE);
                binding.btnDangNhap.setVisibility(View.VISIBLE);
                binding.btnDangKy.setVisibility(View.VISIBLE);
                DataLocalManager.setBooleanValue(false);
                Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            }
        });
        binding.doiqua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tính năng sắp ra mắt", Toast.LENGTH_SHORT).show();
            }
        });
        binding.tichdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tính năng sắp ra mắt", Toast.LENGTH_SHORT).show();
            }
        });
        binding.uudai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Tính năng sắp ra mắt", Toast.LENGTH_SHORT).show();
            }
        });
        binding.lnThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mViewController.addFragment(UserInfoFragment.class, null);
            }
        });
        binding.giaodich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.addFragment(GiaoDichFragment.class, null);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("fat", "onResume account");
        if (DataLocalManager.getInstance() != null && DataLocalManager.getBooleanValue()) {
//            Log.d("mmm","accessTokenSuccess"+DataLocalManager.getAccessToken(),null);
            binding.lnUser.setVisibility(View.VISIBLE);
//            binding.tvtName.setText(DataLocalManager.);
            binding.lnNoUser.setVisibility(View.GONE);
            binding.btnDangNhap.setVisibility(View.GONE);
            binding.btnDangKy.setVisibility(View.GONE);
            mViewModel.getInfo();

        } else {
            Log.d("mmm", "accessTokenFail", null);
            binding.lnUser.setVisibility(View.GONE);
            binding.lnNoUser.setVisibility(View.VISIBLE);
            binding.btnDangNhap.setVisibility(View.VISIBLE);
            binding.btnDangKy.setVisibility(View.VISIBLE);

        }

    }
}


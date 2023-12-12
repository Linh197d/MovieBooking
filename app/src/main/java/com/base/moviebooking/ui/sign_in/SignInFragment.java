package com.base.moviebooking.ui.sign_in;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.DataLocalManager;
import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.DangnhapFragmentBinding;
import com.base.moviebooking.entity.LoginRequest;
import com.base.moviebooking.entity.LoginResponse;
import com.base.moviebooking.ui.account.AccountFragment;
import com.base.moviebooking.ui.sign_up.SignUpFragment;

public class SignInFragment extends BaseFragment<DangnhapFragmentBinding> {

    private SignInViewModel mViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.dangnhap_fragment;
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
        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(SignInViewModel.class);
        mViewModel.dataLogin.observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginRespone) {
                if(loginRespone.getAccessToken()!=null){
                    DataLocalManager.setBooleanValue(true);
                    DataLocalManager.setAccessToken(loginRespone.getAccessToken());
                    Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    mViewController.replaceFragment(AccountFragment.class,null);
                    getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(getContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
                    }
        });

    }

    @Override
    public void initData() {
        eventClick();
    }

    private void eventClick() {
        binding.backDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("fat","back dky",null);
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                mViewController.replaceFragment(AccountFragment.class,null);
            }

        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.edtTk.getText().toString().equals("")||binding.edtMk.getText().toString().equals("")){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                }else{
                    LoginRequest loginRequest = new LoginRequest(binding.edtTk.getText().toString(),binding.edtMk.getText().toString());
                    mViewModel.login(loginRequest);
                }


            }
        });
        binding.btnDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.replaceFragment(SignUpFragment.class,null);
            }
        });
    }


}


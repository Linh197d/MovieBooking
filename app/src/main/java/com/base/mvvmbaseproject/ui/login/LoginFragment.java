package com.base.mvvmbaseproject.ui.login;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.base.mvvmbaseproject.DataLocalManager;
import com.base.mvvmbaseproject.MySharePreferences;
import com.base.mvvmbaseproject.R;
import com.base.mvvmbaseproject.base.BaseFragment;
import com.base.mvvmbaseproject.databinding.FragmentLoginBinding;
import com.base.mvvmbaseproject.entity.LoginData2;
import com.base.mvvmbaseproject.entity.LoginRequest;
import com.base.mvvmbaseproject.entity.LoginResponse2;
import com.base.mvvmbaseproject.ui.home.HomeFragment;
import com.base.mvvmbaseproject.ui.main.MainActivity;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> {
    private LoginViewModel mViewModel;
    LoginRequest loginRequest = new LoginRequest(null, null);

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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
        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
//        sharedPreferences=getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
//        mViewModel.result.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                if (aBoolean) {
////                    mViewController.addFragment(HomeFragment.class,null);
//                }
//            }
//
//        });
        mViewModel.login.observe(getViewLifecycleOwner(), new Observer<LoginResponse2>() {
            @Override
            public void onChanged(LoginResponse2 loginResponse2) {
                Log.d("fat", "" + loginResponse2.getCode());
                if (loginResponse2.getCode() == 200) {
                    // accessToken = loginResponse2.getData().getAccessToken();

                    DataLocalManager.setBooleanValue(true);
                    DataLocalManager.setAccessToken(loginResponse2.getData().getAccessToken());
                    mViewController.addFragment(HomeFragment.class, null);
                    //editor.apply();

                }
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtAccount.getText() != null && binding.edtPassword.getText() != null) {
                    if (!binding.edtAccount.getText().toString().isEmpty() && !binding.edtPassword.getText().toString().isEmpty()) {
                        loginRequest.setUsername(binding.edtAccount.getText().toString());
                        loginRequest.setPassword(binding.edtPassword.getText().toString());
                        mViewModel.loginReq(loginRequest);
                    }
                }
            }
        });
    }


    @Override
    public void initData() {

    }

    @Override
    protected <U> void getObjectResponse(U data) {
        super.getObjectResponse(data);
        if (data instanceof LoginResponse2) {
            Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
        }
    }
}

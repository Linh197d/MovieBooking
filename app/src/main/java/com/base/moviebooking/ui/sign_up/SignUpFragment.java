package com.base.moviebooking.ui.sign_up;

import android.app.DatePickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.DangkyFragmentBinding;
import com.base.moviebooking.entity.RegisterRequest;
import com.base.moviebooking.entity.RegisterResponse;
import com.base.moviebooking.ui.account.AccountFragment;
import com.base.moviebooking.ui.sign_in.SignInFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SignUpFragment extends BaseFragment<DangkyFragmentBinding> {

    final Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int day = calendar.get(Calendar.DAY_OF_MONTH);
    private SignUpViewModel mViewModel;

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
        mViewController.replaceFragment(AccountFragment.class, null);
        return false;
    }

    @Override
    public void initView() {
        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        mViewModel.dataRegister.observe(getViewLifecycleOwner(), new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse response) {
                if (response.isSuccess()) {
                    Toast.makeText(getContext(), "Kiểm tra gmail để xác thực tài khoản", Toast.LENGTH_SHORT).show();
                    mViewController.replaceFragment(SignInFragment.class, null);
                    getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                } else if (response.getData().getMessage().equals("Email đã được sử dụng")) {
                    Toast.makeText(getContext(), "Email đã được sử dụng", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();

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
                Log.d("fat", "back dky", null);
                getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                mViewController.replaceFragment(AccountFragment.class, null);
            }

        });
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtName.getText().toString().equals("") || binding.edtAddress.getText().toString().equals("")
                        || binding.edtBirthday.getText().toString().equals("") || binding.edtEmail.getText().toString().equals("")
                        || binding.edtPassword.getText().toString().equals("") || binding.repeatPass.getText().toString().equals("")
                ) {
                    Toast.makeText(getContext(), "Không được bỏ trống", Toast.LENGTH_SHORT).show();

                } else if (!binding.edtPassword.getText().toString().equals(binding.repeatPass.getText().toString())) {
                    Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();

                } else {
                    SimpleDateFormat stringformat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = null;
                    try {
                        date = stringformat.parse(binding.edtBirthday.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    RegisterRequest registerRequest = new RegisterRequest(binding.edtName.getText().toString()
                            , binding.edtEmail.getText().toString(), binding.edtPassword.getText().toString()
                            , binding.edtAddress.getText().toString(), dateFormat.format(date));
                    mViewModel.register(registerRequest);
                }
            }
        });
        binding.edtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        binding.edtBirthday.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        binding.ToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.replaceFragment(SignInFragment.class, null);
            }
        });
    }


    @NonNull
    @Override
    public DangkyFragmentBinding getViewBinding() {
        return DangkyFragmentBinding.inflate(getLayoutInflater());
    }
}

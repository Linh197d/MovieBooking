package com.base.moviebooking.ui.sign_in;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class SignInFragment extends BaseFragment<DangnhapFragmentBinding> {

    private SignInViewModel mViewModel;
    private Dialog dialog;

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
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel.class);
        mViewModel.dataLogin.observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginRespone) {
                if (loginRespone.getAccessToken() != null) {
                    DataLocalManager.setBooleanValue(true);

                        DataLocalManager.setAccessToken( loginRespone.getAccessToken());

                    try {
                        DataLocalManager.setStringValue(Objects.requireNonNull(binding.edtMk.getText()).toString());
                    } catch (UnrecoverableEntryException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalBlockSizeException e) {
                        throw new RuntimeException(e);
                    } catch (CertificateException e) {
                        throw new RuntimeException(e);
                    } catch (KeyStoreException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (BadPaddingException e) {
                        throw new RuntimeException(e);
                    } catch (InvalidKeyException e) {
                        throw new RuntimeException(e);
                    }
                    Log.d("linhd", String.valueOf(binding.edtMk.getText()));
                    Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    mViewController.replaceFragment(AccountFragment.class, null);
                    getActivity().findViewById(R.id.bottombar).setVisibility(View.VISIBLE);
                } else if (loginRespone.getData().getMessage() != null) {
                    Toast.makeText(getContext(), loginRespone.getData().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void initData() {
        eventClick();
        mViewModel.dataResponeMK.observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse response) {
                if(response.isSuccess()){
                    Toast.makeText(requireContext(),"Kiểm tra gmail để xác thực tài khoản",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(requireContext(),response.getData().getMessage(),Toast.LENGTH_SHORT).show();
                    quenMK();
                }
            }
        });
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
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.edtTk.getText().toString().equals("") || binding.edtMk.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    LoginRequest loginRequest = new LoginRequest(binding.edtTk.getText().toString(), binding.edtMk.getText().toString());
                    mViewModel.login(loginRequest);
                }


            }
        });
        binding.btnDky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewController.replaceFragment(SignUpFragment.class, null);
            }
        });
        binding.btnQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quenMK();
            }
        });
    }
    private void quenMK() {
        dialog = new Dialog(requireContext(), R.style.MyAlertDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_huy_ve);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        TextView textView =dialog.findViewById(R.id.title);
        TextView quenMK =dialog.findViewById(R.id.huyVe);
        quenMK.setText("Xác nhận");
        EditText editText =dialog.findViewById(R.id.edtMK);
        editText.setVisibility(View.VISIBLE);
        textView.setText("Quên mật khẩu");
        dialog.show();

        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        quenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().equals(""))
                    Toast.makeText(requireContext(),"Email trống",Toast.LENGTH_SHORT).show();
                else {
                    mViewModel.quenMK(new ForgetPass(editText.getText().toString()));
                }
            }
        });
    }


    @NonNull
    @Override
    public DangnhapFragmentBinding getViewBinding() {
        return DangnhapFragmentBinding.inflate(getLayoutInflater());
    }
}


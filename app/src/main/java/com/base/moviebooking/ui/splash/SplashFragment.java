package com.base.moviebooking.ui.splash;

import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.base.moviebooking.R;
import com.base.moviebooking.base.BaseFragment;
import com.base.moviebooking.databinding.SplashFragmentBinding;
import com.base.moviebooking.ui.home.HomeFragment;
import com.base.moviebooking.utils.MyUtils;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint

public class SplashFragment extends BaseFragment<SplashFragmentBinding> {

    private SplashViewModel mViewModel;

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {
        try {
            MyUtils.getInstance().createKey();
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getActivity().findViewById(R.id.bottombar).setVisibility(View.GONE);
        mViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
    }

    @Override
    public void initData() {
        new Handler().postDelayed(()->{
            mViewController.replaceFragment(HomeFragment.class,null);
        },2000);
    }


    @NonNull
    @Override
    public SplashFragmentBinding getViewBinding() {
        return SplashFragmentBinding.inflate(getLayoutInflater());
    }
}

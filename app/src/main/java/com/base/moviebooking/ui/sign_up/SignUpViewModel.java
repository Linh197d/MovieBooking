package com.base.moviebooking.ui.sign_up;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.RegisterRequest;
import com.base.moviebooking.entity.RegisterResponse;
import com.base.moviebooking.network.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class SignUpViewModel extends ViewModel {
    MutableLiveData<RegisterResponse> dataRegister = new MutableLiveData<>();
    private Repository repository;

    @Inject
    public SignUpViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<RegisterResponse> getRegisterResponse() {
        return dataRegister;
    }

    public void register(RegisterRequest registerRequest) {
        repository.getRegisterResponse(registerRequest)
                .subscribe(new SingleObserver<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(RegisterResponse response) {
                        dataRegister.postValue(response);
                        Log.d("fat", "success Register");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }


}
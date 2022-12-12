package com.base.mvvmbaseproject.ui.login;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvmbaseproject.base.BaseViewModel;
import com.base.mvvmbaseproject.base.ObjectResponse;
import com.base.mvvmbaseproject.entity.LoginData2;
import com.base.mvvmbaseproject.entity.LoginRequest;
import com.base.mvvmbaseproject.entity.LoginResponse2;
import com.base.mvvmbaseproject.network.repository.Repository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


public class LoginViewModel extends BaseViewModel {
    private Repository repository;
    MutableLiveData<LoginResponse2> login = new MutableLiveData<>();
    MutableLiveData<Boolean> result = new MutableLiveData<Boolean>();

    @Inject
    public LoginViewModel(Repository repository) {
        this.repository = repository;
    }


    public void loginReq(LoginRequest loginRequest) {
        //mDisposable.add(
        repository.rqlogin(loginRequest)
                /*.doOnSubscribe(disposable -> {
                    login.setValue(new ObjectResponse<LoginResponse2>().loading());

                })
                .subscribe(
                        response -> {
                            Log.d("fat","success");
                            login.setValue(new ObjectResponse<LoginResponse2>().success(response.getData()));
                            result=true;
                        },
                        throwable -> {
                            result = false;
                           login.setValue(new ObjectResponse<LoginResponse2>().error(throwable));
                        }
                )*/
                .subscribe(new SingleObserver<LoginResponse2>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(LoginResponse2 loginResponse2) {
                        result.postValue(true);
                        login.postValue(loginResponse2);
                        Log.d("fat", "success");
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.postValue(false);
                        Log.d("fat", "error" + e.getMessage());
                    }
                });
        //);
    }
}
package com.base.moviebooking.ui.change_pass;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.ChangePass;
import com.base.moviebooking.entity.LoginResponse;
import com.base.moviebooking.network.repository.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
@HiltViewModel

public class ChangePassViewModel extends ViewModel {
    private Repository repository;
    MutableLiveData<LoginResponse> dataUser = new MutableLiveData<>();

    public MutableLiveData<LoginResponse> getDataUser() {
        return dataUser;
    }

    @Inject
    public ChangePassViewModel(Repository repository) {
        this.repository = repository;
    }


    public void changePass(ChangePass changePass) {
        repository.changePassword(changePass)
                .subscribe(new SingleObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(LoginResponse response) {
                        dataUser.postValue(response);
                        Log.d("fat", "success get Data User");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }


}

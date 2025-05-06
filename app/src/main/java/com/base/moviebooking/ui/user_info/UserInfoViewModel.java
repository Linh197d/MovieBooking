package com.base.moviebooking.ui.user_info;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Account;
import com.base.moviebooking.entity.RegisterResponse;
import com.base.moviebooking.entity.UserUpdate;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class UserInfoViewModel extends ViewModel {
    private Repository repository;
    MutableLiveData<List<Account>> dataUser = new MutableLiveData<>();
    MutableLiveData<RegisterResponse> update = new MutableLiveData<>();

    public MutableLiveData<List<Account>> getDataUser() {
        return dataUser;
    }
    @Inject
    public UserInfoViewModel(Repository repository) {
        this.repository = repository;
    }


    public void getInfo( ) {
        repository.getInfoUser()
                .subscribe(new SingleObserver<List<Account>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Account> account) {
                        dataUser.postValue(account);
                        Log.d("fat", "success get Data User");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    public void updateUser( UserUpdate user) {
        repository.updateUser(user)
                .subscribe(new SingleObserver<RegisterResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(RegisterResponse account) {
                        update.postValue(account);
                        Log.d("fat", "success update User");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }


}
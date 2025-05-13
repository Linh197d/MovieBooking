package com.base.moviebooking.ui.account;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Account;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class AccountViewModel extends ViewModel {
    MutableLiveData<List<Account>> dataUser = new MutableLiveData<>();
    private Repository repository;

    @Inject
    public AccountViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<List<Account>> getDataUser() {
        return dataUser;
    }

    public void getInfo() {
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


}

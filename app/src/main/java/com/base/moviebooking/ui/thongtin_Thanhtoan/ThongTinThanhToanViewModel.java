package com.base.moviebooking.ui.thongtin_Thanhtoan;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.moviebooking.base.BaseViewModel;
import com.base.moviebooking.entity.Account;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class ThongTinThanhToanViewModel extends BaseViewModel {
    private Repository repository;
    MutableLiveData<List<ThongTinThanhToan>> dataThanhToan = new MutableLiveData<>();
    MutableLiveData<List<Account>> dataUser = new MutableLiveData<>();


    @Inject
    public ThongTinThanhToanViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getThongTinThanhToan() {
        repository.getThongtinThanhToan()
                .subscribe(new SingleObserver<List<ThongTinThanhToan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<ThongTinThanhToan> list) {
                        dataThanhToan.postValue(list);
                        Log.d("fat", "successTTTT");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
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







}
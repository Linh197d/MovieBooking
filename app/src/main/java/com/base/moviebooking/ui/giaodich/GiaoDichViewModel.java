package com.base.moviebooking.ui.giaodich;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.moviebooking.base.BaseViewModel;
import com.base.moviebooking.entity.CancelTicket;
import com.base.moviebooking.entity.LoginResponse;
import com.base.moviebooking.entity.ThongTinThanhToan;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class GiaoDichViewModel extends BaseViewModel {
    private Repository repository;
    MutableLiveData<List<ThongTinThanhToan>> dataThanhtoan = new MutableLiveData<>();
    MutableLiveData<LoginResponse> dataRespone = new MutableLiveData<>();

    @Inject
    public GiaoDichViewModel(Repository repository) {
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
                        dataThanhtoan.postValue(list);
                        Log.d("fat", "successTTTT");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }
    public void huyVe(CancelTicket code) {
        repository.cancelTicket(code)
                .subscribe(new SingleObserver<LoginResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(LoginResponse response) {
                        dataRespone.postValue(response);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }


}
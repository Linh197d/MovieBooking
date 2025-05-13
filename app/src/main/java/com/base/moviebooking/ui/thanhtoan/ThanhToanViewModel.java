package com.base.moviebooking.ui.thanhtoan;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Cinema;
import com.base.moviebooking.entity.RegisterResponse;
import com.base.moviebooking.entity.ThanhToan;
import com.base.moviebooking.entity.VNPay;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class ThanhToanViewModel extends ViewModel {
    MutableLiveData<List<Cinema>> dataCinema = new MutableLiveData<>();
    MutableLiveData<RegisterResponse> dataThanhToan = new MutableLiveData<>();
    private Repository repository;

    @Inject
    public ThanhToanViewModel(Repository repository) {
        this.repository = repository;
    }


    public void getCinemaById(int id) {
        repository.getCinemaFromID(id)
                .subscribe(new SingleObserver<List<Cinema>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Cinema> list) {
                        dataCinema.postValue(list);
                        Log.d("fat", "success Register");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    public void getThanhToan(ThanhToan thanhToan) {
        repository.getThanhToan(thanhToan).subscribe(new SingleObserver<RegisterResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(RegisterResponse response) {
                dataThanhToan.postValue(response);
                Log.d("linhd", response.getData().getUrl());

            }

            @Override
            public void onError(Throwable e) {
                Log.d("fat", "fail thanh toan :" + e);
            }
        });
    }

    public void postThanhToanWebview(VNPay vnPay) {
        repository.thanhToanWebview(vnPay).subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(String s) {
                Log.d("fat", "sucess thanh toan Webview ");

            }

            @Override
            public void onError(Throwable e) {
                Log.d("fat", "fail thanh toan Webview ");

            }
        });
    }
}
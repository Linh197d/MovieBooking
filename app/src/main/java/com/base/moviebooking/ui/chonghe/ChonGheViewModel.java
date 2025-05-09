package com.base.moviebooking.ui.chonghe;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.base.moviebooking.entity.Amount;
import com.base.moviebooking.entity.Chair;
import com.base.moviebooking.entity.Product;
import com.base.moviebooking.entity.Seat;
import com.base.moviebooking.network.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
@HiltViewModel

public class ChonGheViewModel extends ViewModel {
    private final Repository repository;
    MutableLiveData<List<Seat>> chair = new MutableLiveData<>();
    MutableLiveData<List<Chair>> chaired = new MutableLiveData<>();
    MutableLiveData<List<Amount>> dataAmount = new MutableLiveData<>();
    MutableLiveData<List<Product>> dataProduct = new MutableLiveData<>();

    public MutableLiveData<List<Seat>> getdataChair() {
        return chair;
    }

    @Inject
    public ChonGheViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getChair() {
        repository.getAllChair()
                .subscribe(new SingleObserver<List<Seat>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(List<Seat> list) {
                        chair.postValue(list);
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "" + e.getMessage());
                    }
                });
    }

    public void getChaired(int id) {
        repository.getChaired(id).subscribe(new SingleObserver<List<Chair>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Chair> list) {
                Log.d("fat", "successListChair");

                chaired.postValue(list);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void getAmount(int dateType, int dayType, int formatId) {
        repository.getAmount(dateType, dayType, formatId).subscribe(new SingleObserver<List<Amount>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Amount> list) {
//                Log.d("fat", "successAmount:"+amount.getAmount()+"-"+amount.getAmount_vip());
                dataAmount.postValue(list);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("fat", "faildAmount:" + e);

            }
        });
    }

    public void getProduct() {
        repository.getProducts().subscribe(new SingleObserver<List<Product>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Product> list) {
//                Log.d("fat", "successAmount:"+amount.getAmount()+"-"+amount.getAmount_vip());
                dataProduct.postValue(list);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("fat", "faildAmount:" + e);

            }
        });

    }
}



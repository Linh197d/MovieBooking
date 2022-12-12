package com.base.mvvmbaseproject.ui.lskhambenh;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvmbaseproject.base.BaseViewModel;
import com.base.mvvmbaseproject.base.ListResponseChild;
import com.base.mvvmbaseproject.base.ListResponse;
import com.base.mvvmbaseproject.entity.LSKhamBenh;
import com.base.mvvmbaseproject.network.repository.Repository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LSkhamBenhViewModel extends BaseViewModel {
    private Repository repository;
    //MutableLiveData<ListLoadmoreReponse<LSKhamBenh>> dataLSKB = new MutableLiveData<>();
    MutableLiveData<ListResponseChild> dataLSKB = new MutableLiveData<>();
    private int index = 1;

    //    public MutableLiveData<ListLoadmoreReponse<LSKhamBenh>> getDataLSKB() {
//        return dataLSKB;
//    }
    public MutableLiveData<ListResponseChild> getDataLSKB() {
        return dataLSKB;
    }

    @Inject
    public LSkhamBenhViewModel(Repository repository) {
        this.repository = repository;
    }

    //    public void search(boolean isRefresh) {
//        if (isRefresh) {
//            index = 1;
//        }
//        mDisposable.add(
//                repository.getLSKB(index)//
//                        .doOnSubscribe(disposable -> {
//                            dataLSKB.postValue(new ListLoadmoreReponse<LSKhamBenh>().loading());
//                        })
//                        .subscribe(
//                                response -> {
//                                    Log.d("mmm", "on succêss LSKB ViewMOdel");
//                                    index++;
//                                    dataLSKB.postValue(new ListLoadmoreReponse<LSKhamBenh>().success(response.getData(), isRefresh,
//                                            index <= response.getTotalPage()));
//                                },
//                                throwable -> {
//                                    Log.d("fat", "errorr LSKBviewmodel" + throwable);
//                                    dataLSKB.postValue(new ListLoadmoreReponse<LSKhamBenh>().error(throwable));
//                                }
//                        )
//        );
//    }
    public void search(int page) {
        repository.getLSKB(page)
                .subscribe(new SingleObserver<ListResponseChild>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ListResponseChild lsKhamBenhListResponse) {
                        dataLSKB.postValue(lsKhamBenhListResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "error LSKBVIewModel" + e.getMessage());
                    }
                });
    }
}




package com.base.mvvmbaseproject.ui.countnotify;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvmbaseproject.base.BaseViewModel;
import com.base.mvvmbaseproject.entity.CountNotify;
import com.base.mvvmbaseproject.network.repository.Repository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class CountNotifyViewModel extends BaseViewModel {
    private final Repository repository;
    MutableLiveData<CountNotify> countNotify = new MutableLiveData<>();


    public MutableLiveData<CountNotify> getDataServiceCK() {
        return countNotify;
    }

    @Inject
    public CountNotifyViewModel(Repository repository) {
        this.repository = repository;
    }
    public void getCountNotify(){
        repository.searchCountNotify()
                .subscribe(new SingleObserver<CountNotify>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(CountNotify searchResponseListResponse) {
                        countNotify.postValue(searchResponseListResponse);
                        Log.d("fat","successCountNotify");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat","CountNotify: "+e.getMessage());
                    }
                });
    }
}

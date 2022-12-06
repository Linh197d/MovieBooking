package com.base.mvvmbaseproject.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvmbaseproject.base.BaseViewModel;
import com.base.mvvmbaseproject.base.ListResponse;
import com.base.mvvmbaseproject.entity.DataServicesCK;
import com.base.mvvmbaseproject.entity.SearchResponse;
import com.base.mvvmbaseproject.network.repository.Repository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HomeViewModel extends BaseViewModel {
    private Repository repository;
    MutableLiveData<ListResponse<SearchResponse>> dataService = new MutableLiveData<>();
    MutableLiveData<ListResponse<DataServicesCK>> dataServiceCK = new MutableLiveData<>();
    public MutableLiveData<ListResponse<DataServicesCK>> getDataServiceCK() {
        return dataServiceCK;
    }
    @Inject
    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ListResponse<SearchResponse>> getDataService() {
        return dataService;
    }

   /* public MutableLiveData<ListLoadmoreReponse<SearchResponse>> getSearch() {
        return search;
    }*/

    public void search(boolean isRefresh) {
      /*  if (isRefresh) {
            pageIndex = 1;
        }
        mDisposable.add(
                repository.search(per_page, pageIndex)//
                        .doOnSubscribe(disposable -> {
                            search.postValue(new ListLoadmoreReponse<SearchResponse>().loading());
                        })
                        .subscribe(
                                response -> {
                                    Log.d("fat", "on succêss HomeViewmodel");
                                    pageIndex++;
                                    search.postValue(new ListLoadmoreReponse<SearchResponse>().success(response.getData(), isRefresh,
                                            pageIndex <= response.getTotalPage()));
                                },
                                throwable -> {
                                    Log.d("fat", "errorr Homeviewmodel" + throwable);
                                    search.postValue(new ListLoadmoreReponse<SearchResponse>().error(throwable));
                                }
                        )
        );*/
        /*repository.search(1,pageIndex)
                .subscribe(new SingleObserver<SearchResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(SearchResponse searchResponseListResponse) {
                        search.postValue(searchResponseListResponse);
                        Log.d("fat","successHomeViewMOdell");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat","eorror: "+e.getMessage());
                    }
                });*/
    }
    public void getData(int pageIndex,int per_page){
        repository.search(pageIndex,per_page)
                .subscribe(new SingleObserver<ListResponse<SearchResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) { }
                    @Override
                    public void onSuccess(ListResponse<SearchResponse> searchResponseListResponse) {
                        dataService.postValue(searchResponseListResponse);
                        Log.d("fat","successHomeViewMOdell");
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat",""+e.getMessage());
                    }
                });
    }
    public void getDataCK(int pageIndex,int per_page){
        repository.searchCK(pageIndex,per_page)
                .subscribe(new SingleObserver<ListResponse<DataServicesCK>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ListResponse<DataServicesCK> searchResponseListResponse) {
                        dataServiceCK.postValue(searchResponseListResponse);
                        Log.d("fat","successCKViewMOdell");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat","CKViewModel"+e.getMessage());
                    }
                });
    }
}




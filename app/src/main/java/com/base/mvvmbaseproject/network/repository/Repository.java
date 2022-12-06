package com.base.mvvmbaseproject.network.repository;

import com.base.mvvmbaseproject.base.ListResponse;
import com.base.mvvmbaseproject.base.ObjectResponse;
import com.base.mvvmbaseproject.entity.CountNotify;
import com.base.mvvmbaseproject.entity.DataServicesCK;
import com.base.mvvmbaseproject.entity.DataUser;
import com.base.mvvmbaseproject.entity.DataUserSK;
import com.base.mvvmbaseproject.entity.LoginRequest;
import com.base.mvvmbaseproject.entity.LoginResponse2;
import com.base.mvvmbaseproject.entity.SearchResponse;
import com.base.mvvmbaseproject.entity.UpdateDataUser;
import com.base.mvvmbaseproject.entity.UpdateDataUserHealth;
import com.base.mvvmbaseproject.entity.UpdateRespone;
import com.base.mvvmbaseproject.network.ApiInterface;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private final ApiInterface apiInterface;

    @Inject
    Repository(ApiInterface apiInterface) {
        this.apiInterface = apiInterface;
    }
    //search service dichvu
    public Single<ListResponse<SearchResponse>> search(int per_page, int querypage) {//int
        return apiInterface.search(per_page, querypage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //search service CK
    public Single<ListResponse<DataServicesCK>> searchCK(int per_page, int querypage) {
        return apiInterface.searchCK(per_page, querypage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //search thong tin user
    public Single<ObjectResponse<DataUser>> searchUser() {
        return apiInterface.searchUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //search thoong bao
    public Single<CountNotify> searchCountNotify() {
        return apiInterface.searchCountNotify()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    // request login
    public Single<LoginResponse2> rqlogin(LoginRequest loginRequest) {
        return apiInterface.requestLogin(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //search thong tin SK user
    public Single<ObjectResponse<DataUserSK>> searchUserSK() {
        return apiInterface.searchDataUserSK()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //update thong tin  user
    public Single<UpdateRespone> update_user_info(UpdateDataUser dataUser) {
        return apiInterface.requestUpdateUser(dataUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //update thong tin SK user
    public Single<LoginResponse2> update_user_health(UpdateDataUserHealth dataUserSK) {
        return apiInterface.requestUpdateUserSK(dataUserSK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

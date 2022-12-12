package com.base.mvvmbaseproject.ui.UserProfile;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.base.mvvmbaseproject.base.BaseViewModel;
import com.base.mvvmbaseproject.base.ObjectResponse;
import com.base.mvvmbaseproject.entity.CountNotify;
import com.base.mvvmbaseproject.entity.DataUser;
import com.base.mvvmbaseproject.entity.DataUserSK;
import com.base.mvvmbaseproject.entity.LoginResponse2;
import com.base.mvvmbaseproject.entity.UpdateDataUser;
import com.base.mvvmbaseproject.entity.UpdateDataUserHealth;
import com.base.mvvmbaseproject.entity.UpdateRespone;
import com.base.mvvmbaseproject.network.repository.Repository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class UserViewModel extends BaseViewModel {
    private Repository repository;
    MutableLiveData<UpdateRespone> update_user = new MutableLiveData<>();
    MutableLiveData<LoginResponse2> update_user_health = new MutableLiveData<>();

    MutableLiveData<ObjectResponse<DataUser>> userprofile = new MutableLiveData<>();
    MutableLiveData<ObjectResponse<DataUserSK>> userSK = new MutableLiveData<>();

    public MutableLiveData<UpdateRespone> getUpdate_user() {
        return update_user;
    }

    public MutableLiveData<LoginResponse2> getUpdate_user_health() {
        return update_user_health;
    }

    public MutableLiveData<ObjectResponse<DataUserSK>> getUserSK() {
        return userSK;
    }

    public MutableLiveData<ObjectResponse<DataUser>> getDataUser() {
        return userprofile;
    }

    @Inject
    public UserViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getUserProfile() {
        repository.searchUser()
                .subscribe(new SingleObserver<ObjectResponse<DataUser>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ObjectResponse<DataUser> dataUserObjectResponse) {
                        userprofile.postValue(dataUserObjectResponse);
                        Log.d("mmm", "sucess User");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "error User:" + e.getMessage());
                    }
                });

    }

    public void getUserSucKhoe() {
        repository.searchUserSK()
                .subscribe(new SingleObserver<ObjectResponse<DataUserSK>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(ObjectResponse<DataUserSK> dataUserObjectResponse) {
                        userSK.postValue(dataUserObjectResponse);
                        Log.d("mmm", "sucess UserSK");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "error UserSK:" + e.getMessage());
                    }
                });

    }

    public void updateUser(UpdateDataUser dataUser) {
        repository.update_user_info(dataUser)
                .subscribe(new SingleObserver<UpdateRespone>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(UpdateRespone loginResponse2) {
                        update_user.postValue(loginResponse2);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "error: Update User " + e.getMessage());
                    }
                });
    }

    public void updateUserHealth(UpdateDataUserHealth dataUserSK) {
        repository.update_user_health(dataUserSK)
                .subscribe(new SingleObserver<LoginResponse2>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(LoginResponse2 loginResponse2) {
                        update_user_health.postValue(loginResponse2);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("fat", "error: Update User Health" + e.getMessage());
                    }
                });
    }
}

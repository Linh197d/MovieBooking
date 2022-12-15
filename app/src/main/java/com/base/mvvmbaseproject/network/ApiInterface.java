package com.base.mvvmbaseproject.network;


import com.base.mvvmbaseproject.base.ListResponse;
import com.base.mvvmbaseproject.base.ListResponseChild;
import com.base.mvvmbaseproject.base.ObjectResponse;
import com.base.mvvmbaseproject.entity.CountNotify;
import com.base.mvvmbaseproject.entity.DataServicesCK;
import com.base.mvvmbaseproject.entity.DataUser;
import com.base.mvvmbaseproject.entity.DataUserSK;
import com.base.mvvmbaseproject.entity.DeleteLSKB;
import com.base.mvvmbaseproject.entity.LSKhamBenh;
import com.base.mvvmbaseproject.entity.LoginRequest;
import com.base.mvvmbaseproject.entity.LoginResponse2;
import com.base.mvvmbaseproject.entity.SearchResponse;
import com.base.mvvmbaseproject.entity.UpdateDataUser;
import com.base.mvvmbaseproject.entity.UpdateDataUserHealth;
import com.base.mvvmbaseproject.entity.UpdateRespone;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("user/login")
    Single<LoginResponse2> requestLogin(@Body LoginRequest loginRequest);//

    @GET("user/services")
       Single<ListResponse<SearchResponse>> search(@Query("per_page") int per_page, @Query("querypage") int pageIndex);//
    @GET("user/specialty")
    Single<ListResponse<DataServicesCK>> searchCK(@Query("per_page") int per_page, @Query("querypage") int pageIndex);
    @GET("user")
    Single<ObjectResponse<DataUser>> searchUser();
    ///Get thông báo
    @GET("user/count-notification")
    Single<CountNotify> searchCountNotify();
    //get Data USer Sức khoẻ
    @GET("user/user-file/show")
    Single<ObjectResponse<DataUserSK>> searchDataUserSK();

    @POST("user/update")
    Single<UpdateRespone> requestUpdateUser(@Body UpdateDataUser dataUser);

    @POST("user/user-file/update")
    Single<LoginResponse2> requestUpdateUserSK(@Body UpdateDataUserHealth dataUserSK);
    //get lskb
    @GET("user/medical-history?per_page=20")
    Single<ListResponseChild> getDataLSKB(@Query("page")int page);

    @GET("user/medical-history/delete/{id}")
    Single<ObjectResponse<DeleteLSKB>> getDataDeleteLSKB(@Path("id") int id);
}

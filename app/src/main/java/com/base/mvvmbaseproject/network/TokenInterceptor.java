package com.base.mvvmbaseproject.network;


import androidx.annotation.NonNull;

import com.base.mvvmbaseproject.utils.Define;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {


    public TokenInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        /*String accessToken="";
        if ( DataLocalManager.getAccessToken()!=null){//DataLocalManager.getInstance()!=null &&
            accessToken = DataLocalManager.getAccessToken();
        }*/
        Request.Builder ongoing = chain.request().newBuilder();
        Response response = chain.proceed(ongoing.build());
        int responseCode = response.code();
        //handle token expire here
        /* response = response.newBuilder()
                    .addHeader( "Authorization",
                            "Bearer " + accessToken)
                    .build();
                        Log.d("fatggg","header: "+accessToken);*/
        return response;
    }
}


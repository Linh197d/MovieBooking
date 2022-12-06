package com.base.mvvmbaseproject.network;


import android.util.Log;

import com.base.mvvmbaseproject.DataLocalManager;
import com.base.mvvmbaseproject.utils.Define;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {


    public TokenInterceptor() {
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        /*String accessToken="";
        if ( DataLocalManager.getAccessToken()!=null){//DataLocalManager.getInstance()!=null &&
            accessToken = DataLocalManager.getAccessToken();
        }*/
        Request.Builder ongoing = chain.request().newBuilder();
        Response response = chain.proceed(ongoing.build());
        int responseCode = response.code();
        if (responseCode == Define.Api.Http.RESPONSE_CODE_ACCESS_TOKEN_EXPIRED) {
            //handle token expire here
           /* response = response.newBuilder()
                    .addHeader( "Authorization",
                            "Bearer " + accessToken)
                    .build();
                        Log.d("fatggg","header: "+accessToken);*/

        }
        return response;
    }
}


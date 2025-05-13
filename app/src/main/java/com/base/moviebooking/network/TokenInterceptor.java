package com.base.moviebooking.network;


import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {


    public TokenInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request.Builder ongoing = chain.request().newBuilder();
        Response response = chain.proceed(ongoing.build());
        int responseCode = response.code();
        //handle token expire here

        return response;
    }
}


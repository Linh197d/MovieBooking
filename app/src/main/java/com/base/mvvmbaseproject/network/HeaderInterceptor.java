package com.base.mvvmbaseproject.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.base.mvvmbaseproject.DataLocalManager;
import com.base.mvvmbaseproject.MySharePreferences;
import com.base.mvvmbaseproject.ui.login.LoginFragment;
import com.base.mvvmbaseproject.ui.login.LoginViewModel;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.Headers;

public class HeaderInterceptor implements Interceptor {
    public HeaderInterceptor() {
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String accessToken = "";
        if (DataLocalManager.getInstance() != null && DataLocalManager.getAccessToken() != null) {// &&
            accessToken = DataLocalManager.getAccessToken();
            request = request.newBuilder()
                    .addHeader("device", "2")
                    .addHeader("version", "2.2.2") //new header added
                    .addHeader("lang", "vi")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization",
                            "Bearer " + accessToken)
                    .build();
        } else {
            request = request.newBuilder()
                    .addHeader("device", "2")
                    .addHeader("version", "2.2.2") //new header added
                    .addHeader("lang", "vi")
                    .addHeader("Content-Type", "application/json")
                    .build();
        }
        Response response = chain.proceed(request);
        return response;

    }
}

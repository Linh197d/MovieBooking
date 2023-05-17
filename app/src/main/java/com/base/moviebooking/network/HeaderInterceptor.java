package com.base.moviebooking.network;

import androidx.annotation.NonNull;

import com.base.moviebooking.DataLocalManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    public HeaderInterceptor() {
    }

    @NonNull
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String accessToken;
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
        return chain.proceed(request);

    }
}

package com.base.mvvmbaseproject.di.module;

import android.content.Context;

import com.base.mvvmbaseproject.BuildConfig;
import com.base.mvvmbaseproject.network.ApiInterface;
import com.base.mvvmbaseproject.network.HeaderInterceptor;
import com.base.mvvmbaseproject.network.NetworkCheckerInterceptor;
import com.base.mvvmbaseproject.network.TokenInterceptor;
import com.base.mvvmbaseproject.utils.Define;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface(OkHttpClient client) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        TokenInterceptor tokenInterceptor = new TokenInterceptor();
        HeaderInterceptor headerInterceptor = new HeaderInterceptor();
        NetworkCheckerInterceptor networkCheckerInterceptor = new NetworkCheckerInterceptor(context);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(tokenInterceptor)
                .addInterceptor(headerInterceptor)
                .addInterceptor(networkCheckerInterceptor)
                .connectTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Define.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }
}

package com.base.mvvmbaseproject.di;

import android.app.Application;


import com.base.mvvmbaseproject.BaseApplication;
import com.base.mvvmbaseproject.di.module.ActivityBindingModule;
import com.base.mvvmbaseproject.di.module.AppModule;
import com.base.mvvmbaseproject.di.module.FragmentBindingModule;
import com.base.mvvmbaseproject.di.module.NetworkModule;
import com.base.mvvmbaseproject.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        ViewModelModule.class,
        NetworkModule.class
})
public interface AppComponent {

    void inject(BaseApplication baseApplication);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}

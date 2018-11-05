package com.haoruigang.cniao5play.di.module;

import android.app.Application;

import com.google.gson.Gson;
import com.haoruigang.cniao5play.AppApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    public AppApplication appAplication;

    public AppModule(AppApplication appAplication) {
        this.appAplication = appAplication;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return appAplication;
    }

    @Provides
    @Singleton
    public Gson provideGosn() {
        return new Gson();
    }

}

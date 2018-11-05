package com.haoruigang.cniao5play.di.component;

import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.di.module.AppModule;
import com.haoruigang.cniao5play.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, HttpModule.class})
@Singleton
public interface AppComponent {

    ApiService getApiService();

}

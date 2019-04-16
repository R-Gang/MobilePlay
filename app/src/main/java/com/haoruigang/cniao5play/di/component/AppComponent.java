package com.haoruigang.cniao5play.di.component;

import android.app.Application;

import com.haoruigang.cniao5play.di.module.DownloadModule;
import com.haoruigang.cniao5play.common.rx.RxErrorHandler;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.di.module.AppModule;
import com.haoruigang.cniao5play.di.module.HttpModule;

import javax.inject.Singleton;

import dagger.Component;
import zlc.season.rxdownload2.RxDownload;

@Component(modules = {AppModule.class, HttpModule.class, DownloadModule.class})
@Singleton
public interface AppComponent {

    ApiService getApiService();

    Application getApplication();

    RxErrorHandler getRxErrorHandle();

    RxDownload getRxDownload();

}

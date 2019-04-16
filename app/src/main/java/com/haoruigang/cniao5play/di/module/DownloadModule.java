package com.haoruigang.cniao5play.di.module;

import android.app.Application;
import android.os.Environment;

import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.util.ACache;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import zlc.season.rxdownload2.RxDownload;

/**
 * Date: 2019/4/16
 * Author: haoruigang
 * Description: com.haoruigang.cniao5play.common
 */
@Module
public class DownloadModule {

    @Provides
    @Singleton
    public RxDownload provideRxDownload(Application application, Retrofit retrofit, File downDir) {
        ACache.get(application).put(Constant.APK_DOWNLOAD_DIR, downDir.getPath());
        return RxDownload.getInstance(application)
                .defaultSavePath(downDir.getPath())
                .retrofit(retrofit)
                .maxDownloadNumber(10)
                .maxThread(10);
    }

    @Provides
    @Singleton
    File provideDownloadDir() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }
}

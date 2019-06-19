package com.haoruigang.cniao5play.data;

import android.content.Context;

import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.apkparset.AndroidApk;
import com.haoruigang.cniao5play.common.util.ACache;
import com.haoruigang.cniao5play.presenter.contract.AppManagerContract;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerModel implements AppManagerContract.IAppManagerModel {

    private RxDownload mRxDownload;
    public Context context;

    public AppManagerModel(Context context, RxDownload mRxDownload) {
        this.context = context;
        this.mRxDownload = mRxDownload;
    }

    @Override
    public Observable<List<DownloadRecord>> getDownloadRecord() {
        return mRxDownload.getTotalDownloadRecords();
    }

    @Override
    public RxDownload getRxDownload() {
        return mRxDownload;
    }

    @Override
    public Observable<List<AndroidApk>> getLocalApks() {
        String dir = ACache.get(context).getAsString(Constant.APK_DOWNLOAD_DIR);
        return Observable.create(e -> {
            e.onNext(scanApks(dir));
            e.onComplete();
        });
    }

    private List<AndroidApk> scanApks(String dir) {
        File file = new File(dir);
        if (!file.isDirectory()) {
            throw new RuntimeException("is not Dir");
        }
        File[] apks = file.listFiles(f -> {
            if (f.isDirectory()) {
                return false;
            }
            return f.getName().endsWith(".apk");
        });
        List<AndroidApk> androidApks = new ArrayList<>();
        for (File apk : apks) {
            AndroidApk androidApk = AndroidApk.read(context, apk.getPath());
            androidApks.add(androidApk);
        }
        return androidApks;
    }

}

package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.apkparset.AndroidApk;
import com.haoruigang.cniao5play.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public interface AppManagerContract {


    interface AppManagerView extends BaseView {

        void showDownloading(List<DownloadRecord> downloadRecords);

        void showApps(List<AndroidApk> androidApks);

        void showUpdateApps(List<AppInfoBean> appinfo);
    }

    interface IAppManagerModel {

        Observable<List<DownloadRecord>> getDownloadRecord();

        RxDownload getRxDownload();

        Observable<List<AndroidApk>> getLocalApks();

        Observable<List<AndroidApk>> getInstalledApps();
    }

}

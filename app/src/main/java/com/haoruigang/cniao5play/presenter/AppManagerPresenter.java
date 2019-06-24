package com.haoruigang.cniao5play.presenter;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.apkparset.AndroidApk;
import com.haoruigang.cniao5play.common.rx.RxSchedulers;
import com.haoruigang.cniao5play.common.rx.observer.ProgressObserver;
import com.haoruigang.cniao5play.common.util.ACache;
import com.haoruigang.cniao5play.presenter.contract.AppManagerContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadFlag;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerPresenter extends BasePresenter<AppManagerContract.IAppManagerModel, AppManagerContract.AppManagerView> {

    @Inject
    public AppManagerPresenter(AppManagerContract.IAppManagerModel appManagerModel, AppManagerContract.AppManagerView appManagerView) {
        super(appManagerModel, appManagerView);
    }

    public void getDownloadingApps() {
        mModel.getDownloadRecord().compose(RxSchedulers.<List<DownloadRecord>>io_main())
                .subscribe(new ProgressObserver<List<DownloadRecord>>(mContext, mRootView) {
                    @Override
                    public void onNext(List<DownloadRecord> downloadRecords) {
                        mRootView.showDownloading(downloadRecordFilter(downloadRecords));
                    }
                });
    }

    public void getLocalApks() {
        mModel.getLocalApks().compose(RxSchedulers.<List<AndroidApk>>io_main())
                .subscribe(new ProgressObserver<List<AndroidApk>>(mContext, mRootView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mRootView.showApps(androidApks);
                    }
                });
    }


    public RxDownload getRxDownload() {
        return mModel.getRxDownload();
    }

    private List<DownloadRecord> downloadRecordFilter(List<DownloadRecord> downloadRecords) {
        List<DownloadRecord> newList = new ArrayList<>();
        for (DownloadRecord r : downloadRecords) {
            if (r.getFlag() != DownloadFlag.COMPLETED) {
                newList.add(r);
            }
        }
        return newList;
    }

    public void getInstalledApps() {

        mModel.getInstalledApps().compose(RxSchedulers.<List<AndroidApk>>io_main())
                .subscribe(new ProgressObserver<List<AndroidApk>>(mContext, mRootView) {
                    @Override
                    public void onNext(List<AndroidApk> androidApks) {
                        mRootView.showApps(androidApks);
                    }
                });
    }


    public void getUpdateApps() {

        String json = ACache.get(mContext).getAsString(Constant.APP_UPDATE_LIST);

        if (!TextUtils.isEmpty(json)) {
            Gson gson = new Gson();
            List<AppInfoBean> apps = gson.fromJson(json, new TypeToken<List<AppInfoBean>>() {
            }.getType());
            Observable.just(apps)
                    .compose(RxSchedulers.<List<AppInfoBean>>io_main())
                    .subscribe(new ProgressObserver<List<AppInfoBean>>(mContext, mRootView) {
                        @Override
                        public void onNext(List<AppInfoBean> appInfos) {
                            mRootView.showUpdateApps(appInfos);
                        }
                    });
        }
    }

}

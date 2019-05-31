package com.haoruigang.cniao5play.data;

import com.haoruigang.cniao5play.presenter.contract.AppManagerContract;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class AppManagerModel implements AppManagerContract.IAppManagerModel {

    private RxDownload mRxDownload;

    public AppManagerModel(RxDownload mRxDownload) {
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
}

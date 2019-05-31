package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

public interface AppManagerContract {


    interface AppManagerView extends BaseView {

        void showDownloading(List<DownloadRecord> downloadRecords);

    }

    interface IAppManagerModel {

        Observable<List<DownloadRecord>> getDownloadRecord();

        RxDownload getRxDownload();
    }

}

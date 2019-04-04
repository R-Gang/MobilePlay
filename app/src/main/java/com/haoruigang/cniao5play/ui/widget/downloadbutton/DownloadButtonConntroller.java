package com.haoruigang.cniao5play.ui.widget.downloadbutton;

import android.content.Context;

import com.haoruigang.cniao5play.bean.AppDownloadInfo;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.util.AppUtils;

import java.io.File;

import io.reactivex.Observable;
import zlc.season.rxdownload3.RxDownload;
import zlc.season.rxdownload3.core.Status;

/**
 * Date: 2019/4/1
 * Author: haoruigang
 * Description: com.haoruigang.cniao5play.ui.widget
 */
public class DownloadButtonConntroller {

    private RxDownload rxDownload;
    private String mDownloadDir;// 文件下载的目录

    public static void handClick(DownloadProgressButton btnDownload, AppInfoBean appInfo) {

    }

    /**
     * 已经下载
     *
     * @return
     */
    public Observable<Status> isAppInstalled(Context context, AppInfoBean appInfo) {

        AppUtils.isInstalled(context, appInfo.getPackageName());

        return null;
    }

    /**
     * 未下载,检查文件是否存在
     *
     * @return
     */
    public Observable<Status> isApkFileExsit(AppInfoBean appinfo) {

        String path = mDownloadDir + File.separator + appinfo.getReleaseKeyHash();
        File file = new File(path);
        file.exists();

        return null;
    }

//    public Observable<Status> getDownloadStatus() {
//        return rxDownload.create(mDownloadDir, true);
//    }

    public Observable<AppDownloadInfo> getAppDownloadInfo() {

        return null;
    }

}

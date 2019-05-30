package com.haoruigang.cniao5play.ui.widget.downloadbutton;

import android.content.Context;
import android.util.Log;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppDownloadInfo;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.util.ACache;
import com.haoruigang.cniao5play.common.util.AppUtils;
import com.haoruigang.cniao5play.common.util.PermissionUtil;
import com.jakewharton.rxbinding3.view.RxView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;
import retrofit2.http.Path;
import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadEvent;
import zlc.season.rxdownload2.entity.DownloadFlag;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Date: 2019/4/1
 * Author: haoruigang
 * Description: com.haoruigang.cniao5play.ui.widget
 */
public class DownloadButtonConntroller {

    private RxDownload rxDownload;
//    private String mDownloadDir;// 文件下载的目录

    private Api mApi;

    public DownloadButtonConntroller(RxDownload rxDownload) {
        this.rxDownload = rxDownload;
        if (rxDownload != null) {
            mApi = rxDownload.getRetrofit().create(Api.class);
        }
    }

    public void handClick(final DownloadProgressButton btnDownload, final AppInfoBean appInfo) {
        if (mApi == null) {
            return;
        }
        bindClick(btnDownload, appInfo);
        isAppInstalled(btnDownload.getContext(), appInfo)
                .flatMap((Function<DownloadEvent, ObservableSource<DownloadEvent>>) downloadEvent -> {
                    if (downloadEvent.getFlag() == DownloadFlag.UN_INSTALL) {// 未安装
                        // 检查文件是否存在
                        return isApkFileExsit(btnDownload.getContext(), appInfo);
                    }
                    // 已安装 升级
                    return Observable.just(downloadEvent);
                })
                .flatMap((Function<DownloadEvent, ObservableSource<DownloadEvent>>) downloadEvent -> {
                    if (downloadEvent.getFlag() == DownloadFlag.FILE_EXIST) {// 文件存在
                        return getAppDownloadInfo(appInfo)
                                .flatMap((Function<AppDownloadInfo, ObservableSource<DownloadEvent>>)
                                        downloadInfo -> {
                                            appInfo.setAppDownloadInfo(downloadInfo);
                                            return receiveDownloadStatus(downloadInfo);
                                        });
                    }
                    // 未下载
                    return Observable.just(downloadEvent);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DownloadConsumer(btnDownload, appInfo))
                .isDisposed();
    }

    private void bindClick(final DownloadProgressButton btn, final AppInfoBean appInfo) {
        RxView.clicks(btn).subscribe(unit -> {
            int flag = (int) btn.getTag(R.id.tag_apk_flag);
            Log.d("DownloadConntroller", "flag=" + flag);
            switch (flag) {
                case DownloadFlag.INSTALLED:// 已安装
                    runApp(btn.getContext(), appInfo);
                    break;
                // 升级
                case DownloadFlag.STARTED:// 正在下载
                    pausedDownload(appInfo);
                    break;
                case DownloadFlag.PAUSED:// 已暂停
                case DownloadFlag.NORMAL:// 未下载
                    startDownload(btn, appInfo);
                    break;
                case DownloadFlag.COMPLETED:// 已完成
                    installApp(btn.getContext(), appInfo);
                    break;
            }
        }).isDisposed();
    }

    private void installApp(final Context mContext, final AppInfoBean appInfo) {
//        rxDownload.getRealFiles()
        String path = ACache.get(mContext).getAsString(Constant.APK_DOWNLOAD_DIR)
                + File.separator + appInfo.getReleaseKeyHash();
        AppUtils.installApk(mContext, path);
    }

    private void startDownload(final DownloadProgressButton btnDownload, final AppInfoBean appInfo) {
        PermissionUtil.requestPermisson(btnDownload.getContext(), WRITE_EXTERNAL_STORAGE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        final AppDownloadInfo downloadInfo = appInfo.getAppDownloadInfo();
                        if (downloadInfo == null) {
                            getAppDownloadInfo(appInfo).subscribe(appdownloadInfo -> {
                                appInfo.setAppDownloadInfo(appdownloadInfo);
                                download(btnDownload, appInfo);
                            }).isDisposed();
                        } else {
                            appInfo.setAppDownloadInfo(downloadInfo);
                            download(btnDownload, appInfo);
                        }
                    }
                }).isDisposed();
    }

    public void download(final DownloadProgressButton btnDownload, final AppInfoBean appInfo) {
        rxDownload.serviceDownload(appInfo.getAppDownloadInfo().getDownloadUrl(), appInfo.getReleaseKeyHash()).subscribe();
        rxDownload.receiveDownloadStatus(appInfo.getAppDownloadInfo().getDownloadUrl())
                .subscribe(new DownloadConsumer(btnDownload, appInfo)).isDisposed();
    }

    private void pausedDownload(final AppInfoBean appInfo) {
        rxDownload.pauseServiceDownload(appInfo.getAppDownloadInfo().getDownloadUrl()).subscribe();
    }

    private void runApp(final Context mContext, final AppInfoBean appInfo) {
        AppUtils.runApp(mContext, appInfo.getPackageName());
    }

    /**
     * 已经下载(是否安装)
     *
     * @return
     */
    public Observable<DownloadEvent> isAppInstalled(final Context context, final AppInfoBean appInfo) {

        DownloadEvent event = new DownloadEvent();
        event.setFlag(AppUtils.isInstalled(context, appInfo.getPackageName())
                ? DownloadFlag.INSTALLED : DownloadFlag.UN_INSTALL);

        return Observable.just(event);
    }

    /**
     * 未下载,检查文件是否存在
     *
     * @return
     */
    public Observable<DownloadEvent> isApkFileExsit(final Context mContext, final AppInfoBean appinfo) {

        String path = ACache.get(mContext).getAsString(Constant.APK_DOWNLOAD_DIR)
                + File.separator + appinfo.getReleaseKeyHash();
        File file = new File(path);

        DownloadEvent event = new DownloadEvent();
        event.setFlag(file.exists() ? DownloadFlag.FILE_EXIST : DownloadFlag.NORMAL);

        return Observable.just(event);
    }

    public Observable<DownloadEvent> receiveDownloadStatus(final AppDownloadInfo downloadInfo) {
        return rxDownload.receiveDownloadStatus(downloadInfo.getDownloadUrl());
    }

    public Observable<AppDownloadInfo> getAppDownloadInfo(final AppInfoBean infoBean) {
        return mApi.getAppDownloadInfo(infoBean.getId()).compose(RxHttpResponseCompat.compatResult());
    }

    class DownloadConsumer implements Consumer<DownloadEvent> {
        DownloadProgressButton btnDownload;
        AppInfoBean mAppInfo;

        public DownloadConsumer(final DownloadProgressButton btnDownload, AppInfoBean mAppInfo) {
            this.btnDownload = btnDownload;
            this.mAppInfo = mAppInfo;
        }

        @Override
        public void accept(DownloadEvent downloadEvent) {
            int flag = downloadEvent.getFlag();
            btnDownload.setTag(R.id.tag_apk_flag, flag);
            bindClick(btnDownload, mAppInfo);
            switch (flag) {
                case DownloadFlag.INSTALLED:// 已安装
                    btnDownload.setText("运行");
                    break;
                case DownloadFlag.STARTED:// 正在下载
                    btnDownload.setProgress((int) downloadEvent.getDownloadStatus().getPercentNumber());
                    break;
                case DownloadFlag.PAUSED:// 已暂停
                    btnDownload.setProgress((int) downloadEvent.getDownloadStatus().getPercentNumber());
                    btnDownload.paused();
                    break;
                case DownloadFlag.NORMAL:// 未下载
                    btnDownload.download();
                    break;
                case DownloadFlag.COMPLETED:// 已完成
                    btnDownload.setText("安装");
                    break;
                case DownloadFlag.FAILED:// 失败
                    btnDownload.setText("失败");
                    break;
                case DownloadFlag.DELETED:// 已删除

                    break;
            }
        }
    }

}

interface Api {
    @GET("download/{id}")
    Observable<BaseBean<AppDownloadInfo>> getAppDownloadInfo(@Path("id") int id);
}

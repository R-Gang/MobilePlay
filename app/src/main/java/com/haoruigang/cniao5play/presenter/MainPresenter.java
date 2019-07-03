package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.requestbean.AppsUpdateBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.apkparset.AndroidApk;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ProgressObserver;
import com.haoruigang.cniao5play.common.util.ACache;
import com.haoruigang.cniao5play.common.util.AppUtils;
import com.haoruigang.cniao5play.common.util.JsonUtils;
import com.haoruigang.cniao5play.common.util.PermissionUtil;
import com.haoruigang.cniao5play.presenter.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

import static android.Manifest.permission.READ_PHONE_STATE;

public class MainPresenter extends BasePresenter<MainContract.IMainModel, MainContract.MainView> {

    @Inject
    public MainPresenter(MainContract.IMainModel iMainModel, MainContract.MainView mainView) {
        super(iMainModel, mainView);
    }


    public void requestPermisson() {

        PermissionUtil.requestPermisson(mContext, READ_PHONE_STATE).doOnNext(aBoolean -> {
            if (!aBoolean) {
                mRootView.requestPermissonFail();
            }
        }).subscribe(aBoolean -> mRootView.requestPermissonSuccess()).isDisposed();

    }


    public void getAppUpdateInfo() {
        getIntalledApps()
                .flatMap((Function<AppsUpdateBean, ObservableSource<List<AppInfoBean>>>) params ->
                        mModel.getUpdateApps(params).compose(RxHttpResponseCompat.<List<AppInfoBean>>compatResult()))
                .subscribe(new ProgressObserver<List<AppInfoBean>>(mContext, mRootView) {
                    @Override
                    public void onNext(List<AppInfoBean> appInfos) {
                        if (appInfos != null) {
                            ACache.get(mContext).put(Constant.APP_UPDATE_LIST, JsonUtils.toJson(appInfos));
                        }
                        mRootView.changeAppNeedUpdateCount(appInfos == null ? 0 : appInfos.size());
                    }
                });

    }


    private Observable<AppsUpdateBean> getIntalledApps() {

        return Observable.create(e -> {
            e.onNext(buildParams(AppUtils.getInstalledApps(mContext)));
            e.onComplete();
        });

    }


    private AppsUpdateBean buildParams(List<AndroidApk> apks) {

        StringBuilder packageNameBuilder = new StringBuilder();
        StringBuilder versionCodeBuilder = new StringBuilder();

        for (AndroidApk apk : apks) {

            if (!apk.isSystem()) {

                packageNameBuilder.append(apk.getPackageName()).append(",");
                versionCodeBuilder.append(apk.getAppVersionCode()).append(",");
            }
        }

        AppsUpdateBean param = new AppsUpdateBean();
        param.setPackageName(packageNameBuilder.toString());
        param.setVersionCode(versionCodeBuilder.toString());

        return param;

    }

}

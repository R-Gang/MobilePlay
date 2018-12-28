package com.haoruigang.cniao5play.presenter;

import android.Manifest;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ProgressObserver;
import com.haoruigang.cniao5play.data.RecommendModel;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }

    public void requestPermission() {
        RxPermissions rxPermissions = new RxPermissions((Fragment) mRootView);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) { // Always true pre-M
                            // I can control the camera now
                            mRootView.onRequestPermissionSuccess();
                        } else {
                            // Oups permission denied
                            mRootView.onRequestPermissionFail();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void requestDatas() {
        RxPermissions rxPermissions = new RxPermissions((Fragment) mRootView);
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
                .flatMap(new Function<Boolean, ObservableSource<PageBean<AppInfo>>>() {
                    @Override
                    public ObservableSource<PageBean<AppInfo>> apply(Boolean aBoolean) {
                        if (aBoolean) { // Always true pre-M
                            // I can control the camera now
//                            mRootView.onRequestPermissionSuccess();
                            return mModel.getApps()
                                    .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult());
                        } else {
                            // Oups permission denied
                            mRootView.onRequestPermissionFail();
                            return Observable.empty();
                        }
                    }
                })
                .subscribe(new ProgressObserver<PageBean<AppInfo>>(mContext, mRootView) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {
                            mRootView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mRootView.showNoData();
                        }
                    }
                });
//        mModel.getApps()
//                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
//                .subscribe(new ProgressObserver<PageBean<AppInfo>>(mContext, mRootView) {
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//                        if (appInfoPageBean != null) {
//                            mRootView.showResult(appInfoPageBean.getDatas());
//                        } else {
//                            mRootView.showNoData();
//                        }
//                    }
//                });
    }
}

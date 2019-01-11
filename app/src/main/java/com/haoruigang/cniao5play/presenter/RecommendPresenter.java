package com.haoruigang.cniao5play.presenter;

import android.Manifest;
import android.support.v4.app.Fragment;

import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ProgressObserver;
import com.haoruigang.cniao5play.data.AppInfoModel;
import com.haoruigang.cniao5play.presenter.contract.AppInfoContract;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 推荐
 */
public class RecommendPresenter extends BasePresenter<AppInfoContract.IAppInfoModel, AppInfoContract.View> {

    @Inject
    public RecommendPresenter(AppInfoModel model, AppInfoContract.View view) {
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
        mModel.index().compose(RxHttpResponseCompat.<IndexBean>compatResult())
                .subscribe(new ProgressObserver<IndexBean>(mContext, mRootView) {
                    @Override
                    public void onNext(IndexBean indexBean) {
                        mRootView.showResult(indexBean);
                    }
                });
    }
}

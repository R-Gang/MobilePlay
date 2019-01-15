package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ProgressObserver;
import com.haoruigang.cniao5play.presenter.contract.AppDetailContract;

import javax.inject.Inject;

/**
 * App详情
 */
public class AppDetailPresenter extends BasePresenter<AppDetailContract.IAppDetailModule,
        AppDetailContract.AppDetailView> {

    @Inject
    AppDetailPresenter(AppDetailContract.IAppDetailModule iAppDetailModule,
                       AppDetailContract.AppDetailView appDetailView) {
        super(iAppDetailModule, appDetailView);
    }

    public void getAppDetail(int id) {
        mModel.getAppDetail(id)
                .compose(RxHttpResponseCompat.<AppInfoBean>compatResult())
                .subscribe(new ProgressObserver<AppInfoBean>(mContext, mRootView) {
                    @Override
                    public void onNext(AppInfoBean appInfo) {
                        mRootView.showDetail(appInfo);
                    }
                });
    }
}

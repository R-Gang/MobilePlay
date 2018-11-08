package com.haoruigang.cniao5play.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.common.rx.RxErrorHandler;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ProgressDialogObserver;
import com.haoruigang.cniao5play.data.RecommendModel;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;

import javax.inject.Inject;

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {

    private RxErrorHandler rxErrorHandler;

    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view, RxErrorHandler rxErrorHandler) {
        super(model, view);
        this.rxErrorHandler = rxErrorHandler;
    }


    public void requestDatas() {
        mModel.getApps()
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(new ProgressDialogObserver<PageBean<AppInfo>>(mRootView, rxErrorHandler) {
                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {
                            mRootView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mRootView.showNoData();
                        }
                    }

                    @Override
                    protected boolean isShowDialog() {
                        return false;//不显示Dialog
                    }
                });
    }
}

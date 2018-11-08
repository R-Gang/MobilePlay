package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.common.rx.RxErrorHandler;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ErrorHeadleObserver;
import com.haoruigang.cniao5play.data.RecommendModel;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

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
                .subscribe(new ErrorHeadleObserver<PageBean<AppInfo>>(rxErrorHandler) {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mRootView.showLoading();
                    }

                    @Override
                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
                        if (appInfoPageBean != null) {
                            mRootView.showResult(appInfoPageBean.getDatas());
                        } else {
                            mRootView.showNoData();
                        }
                    }

                    @Override
                    public void onComplete() {
                        mRootView.dimissLoading();
                    }
                });
//                .subscribe(new Observer<PageBean<AppInfo>>() {
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(PageBean<AppInfo> appInfoPageBean) {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mRootView.dimissLoading();
//                        //handle error
//                        mRootView.showError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }
}

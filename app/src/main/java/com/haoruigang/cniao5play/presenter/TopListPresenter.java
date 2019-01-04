package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ErrorHeadleObserver;
import com.haoruigang.cniao5play.common.rx.observer.ProgressObserver;
import com.haoruigang.cniao5play.data.AppInfoModel;
import com.haoruigang.cniao5play.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 排行
 */
public class TopListPresenter extends BasePresenter<AppInfoModel, AppInfoContract.TopListView> {

    @Inject
    TopListPresenter(AppInfoModel appInfoModel, AppInfoContract.TopListView topListView) {
        super(appInfoModel, topListView);
    }

    public void getTopListApps(int page) {

        Observer observer = null;
        if (page == 0) {
            // 第一页显示 loading
            observer = new ProgressObserver<PageBean<AppInfo>>(mContext, mRootView) {
                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mRootView.showResult(appInfoPageBean);
                }
            };
        } else {
            // 加载下一页
            observer = new ErrorHeadleObserver<PageBean<AppInfo>>(mContext) {

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppInfo> appInfoPageBean) {
                    mRootView.showResult(appInfoPageBean);
                }

                @Override
                public void onComplete() {
                    mRootView.onLoadMoreComplete();
                }
            };
        }

        mModel.topList(page)
                .compose(RxHttpResponseCompat.<PageBean<AppInfo>>compatResult())
                .subscribe(observer);

    }
}

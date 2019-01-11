package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.common.rx.RxHttpResponseCompat;
import com.haoruigang.cniao5play.common.rx.observer.ErrorHeadleObserver;
import com.haoruigang.cniao5play.common.rx.observer.ProgressObserver;
import com.haoruigang.cniao5play.data.AppInfoModel;
import com.haoruigang.cniao5play.presenter.contract.AppInfoContract;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 排行/游戏
 */
public class AppInfoPresenter extends BasePresenter<AppInfoContract.IAppInfoModel, AppInfoContract.AppInfoView> {

    public static final int TOP_LIST = 0, GAME = 1, FEATURED = 2, TOPLIST = 3, NEWLIST = 4;

    @Inject
    AppInfoPresenter(AppInfoModel appInfoModel, AppInfoContract.AppInfoView topListView) {
        super(appInfoModel, topListView);
    }

    private void request(int type, int page, int categoryId) {
        Observer observer;
        if (page == 0) {
            // 第一页显示 loading
            observer = new ProgressObserver<PageBean<AppInfoBean>>(mContext, mRootView) {
                @Override
                public void onNext(PageBean<AppInfoBean> appInfoPageBean) {
                    mRootView.showResult(appInfoPageBean);
                }
            };
        } else {
            // 加载下一页
            observer = new ErrorHeadleObserver<PageBean<AppInfoBean>>(mContext) {

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(PageBean<AppInfoBean> appInfoPageBean) {
                    mRootView.showResult(appInfoPageBean);
                }

                @Override
                public void onComplete() {
                    mRootView.onLoadMoreComplete();
                }
            };
        }
        getObservable(type, page, categoryId)
                .compose(RxHttpResponseCompat.<PageBean<AppInfoBean>>compatResult())
                .subscribe(observer);
    }

    public void requestData(int type, int page) {
        request(type, page, 0);
    }

    public void requestData(int type, int page, int categoryId) {
        request(type, page, categoryId);
    }

    private Observable<BaseBean<PageBean<AppInfoBean>>> getObservable(int type, int page, int categoryId) {
        switch (type) {
            case TOP_LIST:
                return mModel.topList(page);
            case GAME:
                return mModel.games(page);
            case FEATURED:
                return mModel.featuredBycategory(categoryId, page);
            case TOPLIST:
                return mModel.topListBycategory(categoryId, page);
            case NEWLIST:
                return mModel.newListBycategory(categoryId, page);
            default:
                return Observable.empty();
        }
    }

}

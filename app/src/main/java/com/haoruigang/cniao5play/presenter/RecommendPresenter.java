package com.haoruigang.cniao5play.presenter;

import android.support.annotation.NonNull;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.data.RecommendModel;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }


    public void requestDatas() {
        mModel.getApps()
                .subscribeOn(Schedulers.io())//前面请求的网络放入线程
                .observeOn(AndroidSchedulers.mainThread())//下面要执行的请求切换成主线程
                .subscribe(new Observer<PageBean<AppInfo>>() {

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
                    public void onError(Throwable e) {
                        mRootView.dimissLoading();
                        //handle error
                        mRootView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        mRootView.dimissLoading();
                    }
                });
    }
}

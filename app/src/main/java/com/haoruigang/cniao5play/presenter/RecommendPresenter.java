package com.haoruigang.cniao5play.presenter;

import android.support.annotation.NonNull;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.data.RecommendModel;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendPresenter extends BasePresenter<RecommendModel, RecommendContract.View> {


    @Inject
    public RecommendPresenter(RecommendModel model, RecommendContract.View view) {
        super(model, view);
    }


    public void requestDatas() {
        mRootView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(@NonNull Call<PageBean<AppInfo>> call, @NonNull Response<PageBean<AppInfo>> response) {
                if (response.body() != null) {
                    mRootView.showResult(response.body().getDatas());
                } else {
                    mRootView.showNoData();
                }
                mRootView.dimissLoading();
            }

            @Override
            public void onFailure(@NonNull Call<PageBean<AppInfo>> call, @NonNull Throwable t) {
                mRootView.showError(t.getMessage());
            }
        });
    }
}

package com.haoruigang.cniao5play.presenter;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.data.RecommendModel;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendPresenter implements RecommendContract.Presenter {


    private RecommendModel mModel;
    private RecommendContract.View mView;

    public RecommendPresenter(RecommendContract.View view, RecommendModel mModel) {
        this.mView = view;
        this.mModel = mModel;
    }

    @Override
    public void requestDatas() {
        mView.showLoading();
        mModel.getApps(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(@NonNull Call<PageBean<AppInfo>> call, @NonNull Response<PageBean<AppInfo>> response) {
                if (response.body() != null) {
                    mView.showResult(response.body().getDatas());
                } else {
                    mView.showNoData();
                }
                mView.dimissLoading();
            }

            @Override
            public void onFailure(@NonNull Call<PageBean<AppInfo>> call, @NonNull Throwable t) {
                mView.showError(t.getMessage());
            }
        });
    }
}

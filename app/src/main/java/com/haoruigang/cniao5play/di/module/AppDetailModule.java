package com.haoruigang.cniao5play.di.module;

import android.app.ProgressDialog;

import com.haoruigang.cniao5play.data.AppDetailModel;
import com.haoruigang.cniao5play.data.AppInfoModel;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.presenter.contract.AppDetailContract;
import com.haoruigang.cniao5play.presenter.contract.AppInfoContract;
import com.haoruigang.cniao5play.ui.activity.AppDetailActivity;
import com.haoruigang.cniao5play.ui.fragment.AppDetailFragment;
import com.haoruigang.cniao5play.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

@Module
//        (includes = Class.class)
public class AppDetailModule {

    private AppDetailContract.AppDetailView mView;

    public AppDetailModule(AppDetailContract.AppDetailView view) {
        this.mView = view;
    }

    @Provides
    public AppDetailContract.AppDetailView provideView() {
        return mView;
    }

    @Provides
    public AppDetailContract.IAppDetailModule provideModule(ApiService mApiService) {
        return new AppDetailModel(mApiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view) {
        return new ProgressDialog(((AppDetailFragment) view).getActivity());
    }

}

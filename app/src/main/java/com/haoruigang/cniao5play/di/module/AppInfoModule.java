package com.haoruigang.cniao5play.di.module;

import android.app.ProgressDialog;

import com.haoruigang.cniao5play.data.AppInfoModel;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.presenter.contract.AppInfoContract;
import com.haoruigang.cniao5play.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class AppInfoModule {

    private AppInfoContract.AppInfoView mView;

    public AppInfoModule(AppInfoContract.AppInfoView view) {
        this.mView = view;
    }

    @Provides
    public AppInfoContract.AppInfoView provideView() {
        return mView;
    }

    @Provides
    public AppInfoContract.IAppInfoModel provideModule(ApiService mApiService) {
        return new AppInfoModel(mApiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppInfoContract.View view) {
        return new ProgressDialog(((RecommendFragment) view).getActivity());
    }

}

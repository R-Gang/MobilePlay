package com.haoruigang.cniao5play.di.module;

import android.app.ProgressDialog;

import com.haoruigang.cniao5play.data.AppManagerModel;
import com.haoruigang.cniao5play.presenter.contract.AppManagerContract;
import com.haoruigang.cniao5play.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;
import zlc.season.rxdownload2.RxDownload;

@Module
public class AppManagerModule {

    private AppManagerContract.AppManagerView mView;

    public AppManagerModule(AppManagerContract.AppManagerView view) {
        this.mView = view;
    }

    @Provides
    public AppManagerContract.AppManagerView provideView() {
        return mView;
    }

    @Provides
    public AppManagerContract.IAppManagerModel provideModule(RxDownload mRxDownload) {
        return new AppManagerModel(((RecommendFragment) mView).getActivity(),mRxDownload);
    }

    @Provides
    public ProgressDialog provideProgressDialog(AppManagerContract.AppManagerView view) {
        return new ProgressDialog(((RecommendFragment) view).getActivity());
    }

}

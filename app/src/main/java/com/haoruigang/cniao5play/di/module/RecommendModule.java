package com.haoruigang.cniao5play.di.module;

import android.app.ProgressDialog;
import android.content.Context;

import com.haoruigang.cniao5play.data.RecommendModel;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.presenter.RecommendPresenter;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;
import com.haoruigang.cniao5play.ui.fragment.RecommendFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view) {
        this.mView = view;
    }

    @Provides
    public RecommendContract.View provideView() {
        return mView;
    }

    @Provides
    public RecommendModel provideModule(ApiService mApiService) {
        return new RecommendModel(mApiService);
    }

    @Provides
    public ProgressDialog provideProgressDialog(RecommendContract.View view) {
        return new ProgressDialog(((RecommendFragment) view).getActivity());
    }

}

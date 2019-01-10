package com.haoruigang.cniao5play.presenter;

import com.haoruigang.cniao5play.presenter.contract.AppDetailContract;

import javax.inject.Inject;

/**
 * App详情
 */
public class AppDetailPresenter extends BasePresenter<AppDetailContract.IAppDetailModule,
        AppDetailContract.AppDetailView> {

    @Inject
    AppDetailPresenter(AppDetailContract.IAppDetailModule iAppDetailModule,
                       AppDetailContract.AppDetailView appDetailView) {
        super(iAppDetailModule, appDetailView);
    }
}

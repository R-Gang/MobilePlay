package com.haoruigang.cniao5play.ui.fragment;

import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerAppInfoComponent;
import com.haoruigang.cniao5play.di.module.AppInfoModule;
import com.haoruigang.cniao5play.presenter.AppInfoPresenter;
import com.haoruigang.cniao5play.ui.adapter.AppInfoAdapter;

/**
 * 游戏
 */
public class GamesFragment extends BaseAppInfoFragment {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppInfoComponent.builder()
                .appComponent(appComponent)
                .appInfoModule(new AppInfoModule(this))
                .build().injectG(this);

    }

    @Override
    int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder()
                .showPosition(false)
                .showBrief(true)
                .showCategoryName(true)
                .rxDownload(mRxDownload)
                .build();
    }
}

package com.haoruigang.cniao5play.ui.fragment;

import com.haoruigang.cniao5play.presenter.AppInfoPresenter;
import com.haoruigang.cniao5play.ui.adapter.AppInfoAdapter;

/**
 * 游戏
 */
public class GamesFragment extends BaseAppInfoFragment {

    @Override
    int type() {
        return AppInfoPresenter.GAME;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder()
                .showPosition(false)
                .showBrief(false)
                .showCategoryName(true)
                .rxDownload(mRxDownload)
                .build();
    }
}

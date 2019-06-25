package com.haoruigang.cniao5play.ui.fragment;

import com.haoruigang.cniao5play.presenter.AppInfoPresenter;
import com.haoruigang.cniao5play.ui.adapter.AppInfoAdapter;

/**
 * 排行
 */
public class TopListFragment extends BaseAppInfoFragment {

    @Override
    int type() {
        return AppInfoPresenter.TOP_LIST;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder()
                .showPosition(true)
                .showBrief(false)
                .showCategoryName(true)
                .rxDownload(mRxDownload)
                .build();
    }
}

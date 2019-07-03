package com.haoruigang.cniao5play.ui.fragment;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.ui.adapter.AppInfoAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpgradeAppFragment extends AppManagerFragment {

    @Override
    public void init() {
        super.init();
        mPresenter.getUpdateApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        return AppInfoAdapter.builder()
                .updateStatus(true)
                .showPosition(false)
                .showCategoryName(false)
                .rxDownload(mPresenter.getRxDownload())
                .build();
    }

    @Override
    public void showUpdateApps(List<AppInfoBean> appInfos) {
        super.showUpdateApps(appInfos);
    }
}

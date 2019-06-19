package com.haoruigang.cniao5play.ui.fragment;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.common.apkparset.AndroidApk;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerAppManagerComponent;
import com.haoruigang.cniao5play.di.module.AppManagerModule;
import com.haoruigang.cniao5play.ui.adapter.DownloadingAdapter;

import java.util.List;

import zlc.season.rxdownload2.entity.DownloadRecord;

public class DownloadingFragment extends AppManagerFragment {

    private DownloadingAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppManagerComponent.builder()
                .appComponent(appComponent)
                .appManagerModule(new AppManagerModule(this))
                .build().inject(this);
    }

    @Override
    protected DownloadingAdapter setupAdapter() {
        return mAdapter = new DownloadingAdapter(mPresenter.getRxDownload());
    }

    @Override
    public void init() {
        mPresenter.getDownloadingApps();
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
        mAdapter.addData(downloadRecords);
        mAdapter.setEnableLoadMore(false);// 是否开启加载
    }

    @Override
    public void showApps(List<AndroidApk> androidApks) {

    }

    @Override
    public void onLoadMoreRequested() {
        mAdapter.loadMoreComplete();// 加载完成
    }
}

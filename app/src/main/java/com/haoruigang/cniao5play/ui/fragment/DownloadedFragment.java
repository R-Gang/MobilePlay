package com.haoruigang.cniao5play.ui.fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.haoruigang.cniao5play.common.apkparset.AndroidApk;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerAppManagerComponent;
import com.haoruigang.cniao5play.di.module.AppManagerModule;
import com.haoruigang.cniao5play.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * 下载已完成
 */
public class DownloadedFragment extends AppManagerFragment {

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        return new AndroidApkAdapter(AndroidApkAdapter.FLAG_APK);
    }

    @Override
    public void init() {
        super.init();
        mPresenter.getLocalApks();
    }

    @Override
    public void showApps(List<AndroidApk> androidApks) {
        super.showApps(androidApks);
    }
}

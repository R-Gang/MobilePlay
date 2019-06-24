package com.haoruigang.cniao5play.ui.fragment;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.haoruigang.cniao5play.common.apkparset.AndroidApk;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.ui.adapter.AndroidApkAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class InstalledAppAppFragment extends AppManagerFragment {

    @Override
    public void init() {
        super.init();
        mPresenter.getInstalledApps();
    }

    @Override
    protected RecyclerView.Adapter setupAdapter() {
        return new AndroidApkAdapter(AndroidApkAdapter.FLAG_APP);
    }

    @Override
    public void showApps(List<AndroidApk> apps) {
        super.showApps(apps);
    }
}

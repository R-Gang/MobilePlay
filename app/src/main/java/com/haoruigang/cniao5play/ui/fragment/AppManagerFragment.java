package com.haoruigang.cniao5play.ui.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.apkparset.AndroidApk;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerAppManagerComponent;
import com.haoruigang.cniao5play.di.module.AppManagerModule;
import com.haoruigang.cniao5play.presenter.AppManagerPresenter;
import com.haoruigang.cniao5play.presenter.contract.AppManagerContract;
import com.haoruigang.cniao5play.ui.activity.AppDetailActivity;
import com.haoruigang.cniao5play.ui.adapter.AndroidApkAdapter;
import com.haoruigang.cniao5play.ui.adapter.AppInfoAdapter;
import com.haoruigang.cniao5play.ui.adapter.DownloadingAdapter;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.entity.DownloadRecord;

public abstract class AppManagerFragment extends ProgressFragment<AppManagerPresenter> implements AppManagerContract.AppManagerView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.Adapter mAdapter;

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
    public void init() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        //为RecyClerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyClerView设置分割线(这个DividerItemDecoration可以自定义)
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = setupAdapter();
        recyclerView.setAdapter(mAdapter);
        if (mAdapter instanceof DownloadingAdapter) {
            ((DownloadingAdapter) mAdapter).setOnLoadMoreListener(this, recyclerView);// 上拉刷新
            recyclerView.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                    AppInfoBean appInfo = ((DownloadingAdapter) mAdapter).mDownloadButtonConntroller
                            .downloadRecord2AppInfo(((DownloadingAdapter) mAdapter).getItem(position));
                    mApplication.setView(view);
                    startActivity(new Intent(getActivity(), AppDetailActivity.class)
                            .putExtra("appInfo", appInfo));
                }
            });
        } else if (mAdapter instanceof AndroidApkAdapter) {
            ((AndroidApkAdapter) mAdapter).setOnLoadMoreListener(this, recyclerView);// 上拉刷新
        }
    }

    @Override
    public void showApps(List<AndroidApk> androidApks) {
        if (mAdapter instanceof AndroidApkAdapter) {
            ((AndroidApkAdapter) mAdapter).addData(androidApks);
            ((AndroidApkAdapter) mAdapter).setEnableLoadMore(false);// 是否开启加载
        }
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
        if (mAdapter instanceof DownloadingAdapter) {
            ((DownloadingAdapter) mAdapter).addData(downloadRecords);
            ((DownloadingAdapter) mAdapter).setEnableLoadMore(false);// 是否开启加载
        }
    }

    @Override
    public void showUpdateApps(List<AppInfoBean> appInfos) {
        if (mAdapter instanceof AppInfoAdapter) {
            ((AppInfoAdapter) mAdapter).addData(appInfos);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        if (mAdapter instanceof DownloadingAdapter) {
            ((DownloadingAdapter) mAdapter).loadMoreComplete();// 加载完成
        } else if (mAdapter instanceof AndroidApkAdapter) {
            ((AndroidApkAdapter) mAdapter).loadMoreComplete();// 加载完成
        }
    }

    protected abstract RecyclerView.Adapter setupAdapter();

}

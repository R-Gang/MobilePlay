package com.haoruigang.cniao5play.ui.fragment;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerAppManagerComponent;
import com.haoruigang.cniao5play.di.module.AppManagerModule;
import com.haoruigang.cniao5play.presenter.AppManagerPresenter;
import com.haoruigang.cniao5play.presenter.contract.AppManagerContract;
import com.haoruigang.cniao5play.ui.adapter.DownloadingAdapter;

import java.util.List;

import butterknife.BindView;
import zlc.season.rxdownload2.entity.DownloadRecord;

public class DownloadingFragment extends ProgressFragment<AppManagerPresenter> implements AppManagerContract.AppManagerView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

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

    private void setupRecyclerView() {
        //为RecyClerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyClerView设置分割线(这个DividerItemDecoration可以自定义)
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new DownloadingAdapter(mPresenter.getRxDownload());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(DownloadingFragment.this, recyclerView);// 上拉刷新
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
//                AppInfoBean appInfo = mAdapter.getItem(position);
//                mApplication.setView(view);
//                startActivity(new Intent(getActivity(), AppDetailActivity.class)
//                        .putExtra("appInfo", appInfo));
            }
        });
    }

    @Override
    public void init() {
        setupRecyclerView();
        mPresenter.getDownloadingApps();
    }

    @Override
    public void showDownloading(List<DownloadRecord> downloadRecords) {
        mAdapter.addData(downloadRecords);
    }

    @Override
    public void onLoadMoreRequested() {

    }
}

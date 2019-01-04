package com.haoruigang.cniao5play.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.presenter.AppInfoPresenter;
import com.haoruigang.cniao5play.presenter.contract.AppInfoContract;
import com.haoruigang.cniao5play.ui.adapter.AppInfoAdapter;

import butterknife.BindView;

/**
 * 排行/游戏 共用基类
 */
public abstract class BaseAppInfoFragment extends BaseFragment<AppInfoPresenter>
        implements AppInfoContract.AppInfoView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    protected AppInfoAdapter mAdapter;

    int page = 0;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void init() {
        mPresenter.requestData(type(), page);
        initRecyclerView();
    }

    abstract int type();

    abstract AppInfoAdapter buildAdapter();

    private void initRecyclerView() {
        //为RecyClerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyClerView设置分割线(这个DividerItemDecoration可以自定义)
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = buildAdapter();
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this, recyclerView);// 上拉刷新
    }

    @Override
    public void showResult(PageBean<AppInfo> appInfoBean) {
        mAdapter.addData(appInfoBean.getDatas());
        if (appInfoBean.isHasMore()) {
            page++;
        }
        mAdapter.setEnableLoadMore(appInfoBean.isHasMore());// 是否开启加载
    }

    @Override
    public void onLoadMoreComplete() {
        mAdapter.loadMoreComplete();// 加载完成
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.requestData(type(), page);
    }
}

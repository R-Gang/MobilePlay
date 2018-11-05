package com.haoruigang.cniao5play.ui.fragment;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.haoruigang.cniao5play.AppApplication;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.di.component.DaggerRecommendComponent;
import com.haoruigang.cniao5play.di.module.RecommendModule;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;
import com.haoruigang.cniao5play.ui.adapter.RecommendAdapter;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推荐
 */
public class RecommendFragment extends Fragment implements RecommendContract.View {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecommendAdapter mAdapter;

    @Inject
    ProgressDialog mProgressDialog;
    @Inject
    RecommendContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);

        initData();

        return view;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initData() {
        //做了关联，相当于  mPresenter = new RecommendPresenter(this);
        // mProgressDialog = new ProgressDialog(getActivity());
        DaggerRecommendComponent.builder()
                .appComponent(AppApplication.get(Objects.requireNonNull(getActivity())).getmAppComponent())
                .recommendModule(new RecommendModule(this))
                .build().inject(this);//注入

        mPresenter.requestDatas();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initRecyclerView(List<AppInfo> datas) {
        //为RecyClerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyClerView设置分割线(这个DividerItemDecoration可以自定义)
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()), DividerItemDecoration.VERTICAL));
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecommendAdapter(getActivity(), datas);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showResult(List<AppInfo> datas) {
        initRecyclerView(datas);
    }

    @Override
    public void showNoData() {
        Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器异常:" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void dimissLoading() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}

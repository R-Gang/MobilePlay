package com.haoruigang.cniao5play.ui.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.CategoryBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerCategoryComponent;
import com.haoruigang.cniao5play.di.module.CategoryModule;
import com.haoruigang.cniao5play.presenter.CategoryPresenter;
import com.haoruigang.cniao5play.presenter.contract.CategoryContract;
import com.haoruigang.cniao5play.ui.activity.CategoryAppActivity;
import com.haoruigang.cniao5play.ui.adapter.CategoryAdapter;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class CategoryFragment extends ProgressFragment<CategoryPresenter> implements CategoryContract.CategoryView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CategoryAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.template_recycler_view;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerCategoryComponent.builder()
                .appComponent(appComponent)
                .categoryModule(new CategoryModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        initRecyclerView();
        mPresenter.getAllCategory();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initRecyclerView() {
        //为RecyClerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(mApplication));
        //为RecyClerView设置分割线(这个DividerItemDecoration可以自定义)
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),
                DividerItemDecoration.VERTICAL));
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), CategoryAppActivity.class);
                intent.putExtra(Constant.CATEGORY, mAdapter.getData().get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(List<CategoryBean> categoryBean) {
        mAdapter = new CategoryAdapter();
        mAdapter.addData(categoryBean);
        recyclerView.setAdapter(mAdapter);
    }

}

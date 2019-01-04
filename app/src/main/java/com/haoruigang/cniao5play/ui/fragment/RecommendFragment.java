package com.haoruigang.cniao5play.ui.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerRecommendComponent;
import com.haoruigang.cniao5play.di.module.RecommendModule;
import com.haoruigang.cniao5play.presenter.RecommendPresenter;
import com.haoruigang.cniao5play.presenter.contract.RecommendContract;
import com.haoruigang.cniao5play.ui.adapter.IndexMutilAdapter;

import butterknife.BindView;

/**
 * 推荐
 */
public class RecommendFragment extends BaseFragment<RecommendPresenter> implements RecommendContract.View {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private IndexMutilAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        //做了关联，相当于  mPresenter = new RecommendPresenter(this);
        // mProgressDialog = new ProgressDialog(getActivity());
        DaggerRecommendComponent.builder()
                .appComponent(appComponent)
                .recommendModule(new RecommendModule(this))
                .build().inject(this);//注入
    }


    @Override
    public void init() {
        initRecyclerView();
//        mPresenter.requestPermission();
        mPresenter.requestDatas();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void initRecyclerView() {
        //为RecyClerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(mApplication));
        //为RecyClerView设置分割线(这个DividerItemDecoration可以自定义)
//        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),
//                DividerItemDecoration.VERTICAL));
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void showResult(IndexBean indexBean) {
        mAdapter = new IndexMutilAdapter(getActivity());
        mAdapter.setData(indexBean);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), "服务器异常:" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionSuccess() {
        mPresenter.requestDatas();
    }

    @Override
    public void onRequestPermissionFail() {
        Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_LONG).show();
    }

}

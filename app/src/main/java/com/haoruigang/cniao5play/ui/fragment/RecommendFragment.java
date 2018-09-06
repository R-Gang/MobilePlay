package com.haoruigang.cniao5play.ui.fragment;

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

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.http.ApiService;
import com.haoruigang.cniao5play.http.HttpManager;
import com.haoruigang.cniao5play.ui.adapter.RecommendAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 推荐
 */
public class RecommendFragment extends Fragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private RecommendAdapter mAdapter;
    private List<AppInfo> datas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        HttpManager.getInstance().getRetrofit().create(ApiService.class).getApps("{“page”:0}").enqueue(new Callback<PageBean<AppInfo>>() {
            @Override
            public void onResponse(Call<PageBean<AppInfo>> call, Response<PageBean<AppInfo>> response) {
                PageBean<AppInfo> pageBean = response.body();
                datas = pageBean.getDatas();
                initRecyclerView();
            }

            @Override
            public void onFailure(Call<PageBean<AppInfo>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initRecyclerView() {
        //为RecyClerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //为RecyClerView设置分割线(这个DividerItemDecoration可以自定义)
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecommendAdapter(getActivity(), datas);
        recyclerView.setAdapter(mAdapter);
    }
}

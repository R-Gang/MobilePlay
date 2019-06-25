package com.haoruigang.cniao5play.ui.fragment;

import android.annotation.SuppressLint;

import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerAppInfoComponent;
import com.haoruigang.cniao5play.di.module.AppInfoModule;
import com.haoruigang.cniao5play.ui.adapter.AppInfoAdapter;

/**
 * 分类（精品、排行、新品）
 */
@SuppressLint("ValidFragment")
public class CategoryAppFragment extends BaseAppInfoFragment {

    private int categoryId, fragmentType;

    private CategoryAppFragment(int categoryId, int fragmentType) {
        this.categoryId = categoryId;
        this.fragmentType = fragmentType;
    }

    public static CategoryAppFragment newInstance(int categoryId, int fragmentType) {
        return new CategoryAppFragment(categoryId, fragmentType);
    }

    @Override
    public void init() {
        mPresenter.requestData(type(), page, categoryId);
        initRecyclerView();
    }

    @Override
    int type() {
        return fragmentType;
    }

    @Override
    AppInfoAdapter buildAdapter() {
        return AppInfoAdapter.builder()
                .showPosition(false)
                .showBrief(true)
                .showCategoryName(false)
                .rxDownload(mRxDownload)
                .build();
    }

}

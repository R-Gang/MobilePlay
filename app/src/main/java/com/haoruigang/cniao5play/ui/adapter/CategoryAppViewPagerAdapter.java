package com.haoruigang.cniao5play.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.haoruigang.cniao5play.ui.bean.FragmentInfo;
import com.haoruigang.cniao5play.ui.fragment.CategoryAppFragment;
import com.haoruigang.cniao5play.ui.fragment.CategoryFragment;
import com.haoruigang.cniao5play.ui.fragment.GamesFragment;
import com.haoruigang.cniao5play.ui.fragment.RecommendFragment;
import com.haoruigang.cniao5play.ui.fragment.TopListFragment;

import java.util.ArrayList;
import java.util.List;

import static com.haoruigang.cniao5play.presenter.AppInfoPresenter.FEATURED;
import static com.haoruigang.cniao5play.presenter.AppInfoPresenter.NEWLIST;
import static com.haoruigang.cniao5play.presenter.AppInfoPresenter.TOPLIST;

/**
 * 分类（精品，排行，新品）适配器
 */
public class CategoryAppViewPagerAdapter extends FragmentStatePagerAdapter {

    List<FragmentInfo> mFragment = new ArrayList<>();

    private int categoryId;

    public CategoryAppViewPagerAdapter(FragmentManager fm, int categoryId) {
        super(fm);
        this.categoryId = categoryId;
        initFragment();
    }

    private void initFragment() {
        mFragment.add(new FragmentInfo("精品", CategoryAppFragment.class));
        mFragment.add(new FragmentInfo("排行", CategoryAppFragment.class));
        mFragment.add(new FragmentInfo("新品", CategoryAppFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        int fragmentType = FEATURED;
        switch (position) {
            case 0:
                fragmentType = FEATURED;
                break;
            case 1:
                fragmentType = TOPLIST;
                break;
            case 2:
                fragmentType = NEWLIST;
                break;
        }
        return CategoryAppFragment.newInstance(categoryId, fragmentType);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragment.get(position).getTitle();
    }
}

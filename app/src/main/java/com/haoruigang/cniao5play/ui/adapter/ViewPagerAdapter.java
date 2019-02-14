package com.haoruigang.cniao5play.ui.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.haoruigang.cniao5play.ui.bean.FragmentInfo;
import com.haoruigang.cniao5play.ui.fragment.CategoryFragment;
import com.haoruigang.cniao5play.ui.fragment.GamesFragment;
import com.haoruigang.cniao5play.ui.fragment.TopListFragment;
import com.haoruigang.cniao5play.ui.fragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List<FragmentInfo> mFragment = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragment();
    }

    private void initFragment() {
        mFragment.add(new FragmentInfo("推荐", RecommendFragment.class));
        mFragment.add(new FragmentInfo("排行", TopListFragment.class));
        mFragment.add(new FragmentInfo("游戏", GamesFragment.class));
        mFragment.add(new FragmentInfo("分类", CategoryFragment.class));
    }

    @Override
    public Fragment getItem(int position) {
        try {
            return (Fragment) mFragment.get(position).getFragment().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
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

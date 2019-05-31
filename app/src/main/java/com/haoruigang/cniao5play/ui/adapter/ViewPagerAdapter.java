package com.haoruigang.cniao5play.ui.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.haoruigang.cniao5play.ui.bean.FragmentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List<FragmentInfo> mFragment = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<FragmentInfo> mFragment) {
        super(fm);
        this.mFragment.clear();
        this.mFragment.addAll(mFragment);
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

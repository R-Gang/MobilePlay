package com.haoruigang.cniao5play.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuidePageAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;

    public GuidePageAdapter(FragmentManager fm) {
        super(fm);

    }

    public void setFragments(List<Fragment> fragments) {
        if (fragments == null) {
            this.fragments = new ArrayList<>();
        } else {
            this.fragments = fragments;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

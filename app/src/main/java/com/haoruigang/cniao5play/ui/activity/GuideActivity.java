package com.haoruigang.cniao5play.ui.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.ui.adapter.GuidePageAdapter;
import com.haoruigang.cniao5play.ui.fragment.GuideFragment;
import com.haoruigang.cniao5play.ui.widget.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 引导页
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;
    @BindView(R.id.btn_start)
    Button btnStart;

    private GuidePageAdapter pageAdapter;

    @Override
    public int setLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(GuideFragment.newInstance(R.mipmap.icon_guide1));
        fragments.add(GuideFragment.newInstance(R.mipmap.icon_guide2));
        fragments.add(GuideFragment.newInstance(R.mipmap.icon_guide3));
        pageAdapter = new GuidePageAdapter(getSupportFragmentManager());
        pageAdapter.setFragments(fragments);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(pageAdapter.getCount());
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(pageAdapter);
        indicator.setViewPager(viewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        btnStart.setVisibility((position == pageAdapter.getCount() - 1) ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.btn_start)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}

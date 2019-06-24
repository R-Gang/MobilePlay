package com.haoruigang.cniao5play.ui.activity;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.ui.adapter.ViewPagerAdapter;
import com.haoruigang.cniao5play.ui.bean.FragmentInfo;
import com.haoruigang.cniao5play.ui.fragment.DownloadedFragment;
import com.haoruigang.cniao5play.ui.fragment.DownloadingFragment;
import com.haoruigang.cniao5play.ui.fragment.InstalledAppAppFragment;
import com.haoruigang.cniao5play.ui.fragment.UpgradeAppFragment;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AppManagerActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @Override
    public int setLayout() {
        return R.layout.activity_app_manager;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        initTabLayout();
    }

    private List<FragmentInfo> initFragment() {
        List<FragmentInfo> mFragment = new ArrayList<>(4);
        mFragment.add(new FragmentInfo("下载", DownloadingFragment.class));
        mFragment.add(new FragmentInfo("已完成", DownloadedFragment.class));
        mFragment.add(new FragmentInfo("更新", UpgradeAppFragment.class));
        mFragment.add(new FragmentInfo("安装", InstalledAppAppFragment.class));
        return mFragment;
    }

    private void initTabLayout() {
        toolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );
        toolbar.setNavigationOnClickListener(v -> finish());
        // -------------------- 课时3：TabLayout_ViewPager_Fragment可滑动的顶部菜单 start -----------------------
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // -------------------- 课时3：TabLayout_ViewPager_Fragment可滑动的顶部菜单 end -----------------------
    }

}

package com.haoruigang.cniao5play.ui.activity;

import com.google.android.material.tabs.TabLayout;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.CategoryBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.ui.adapter.CategoryAppViewPagerAdapter;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * 分类（精品、排行、新品）
 */
public class CategoryAppActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    private CategoryBean categoryBean;

    @Override
    public int setLayout() {
        return R.layout.activity_cateogry_app;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        getData();

        initTabLayout();
    }

    private void getData() {
        categoryBean = (CategoryBean) getIntent().getSerializableExtra(Constant.CATEGORY);
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
        PagerAdapter adapter = new CategoryAppViewPagerAdapter(getSupportFragmentManager(), categoryBean.getId());
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // -------------------- 课时3：TabLayout_ViewPager_Fragment可滑动的顶部菜单 end -----------------------
    }

}

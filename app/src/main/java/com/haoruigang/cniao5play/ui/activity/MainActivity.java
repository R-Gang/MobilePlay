package com.haoruigang.cniao5play.ui.activity;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.LoginBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.font.HrgFont;
import com.haoruigang.cniao5play.common.imageloader.GlideCircleTransform;
import com.haoruigang.cniao5play.common.util.ACache;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerMainComponent;
import com.haoruigang.cniao5play.di.module.MainModule;
import com.haoruigang.cniao5play.presenter.MainPresenter;
import com.haoruigang.cniao5play.presenter.contract.MainContract;
import com.haoruigang.cniao5play.ui.adapter.ViewPagerAdapter;
import com.haoruigang.cniao5play.ui.bean.FragmentInfo;
import com.haoruigang.cniao5play.ui.fragment.CategoryFragment;
import com.haoruigang.cniao5play.ui.fragment.GamesFragment;
import com.haoruigang.cniao5play.ui.fragment.RecommendFragment;
import com.haoruigang.cniao5play.ui.fragment.TopListFragment;
import com.haoruigang.cniao5play.ui.widget.BadgeActionProvider;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 主页
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    View headerView;
    ImageView ivUserHeadView;
    TextView tvUsername;

    private BadgeActionProvider badgeActionProvider;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {
        mPresenter.requestPermisson();
        mPresenter.getAppUpdateInfo();
    }

    private List<FragmentInfo> initFragment() {
        List<FragmentInfo> mFragment = new ArrayList<>(4);
        mFragment.add(new FragmentInfo("推荐", RecommendFragment.class));
        mFragment.add(new FragmentInfo("排行", TopListFragment.class));
        mFragment.add(new FragmentInfo("游戏", GamesFragment.class));
        mFragment.add(new FragmentInfo("分类", CategoryFragment.class));
        return mFragment;
    }

    private void initToolbar() {

        // ----------------------------- 课时2：DrawerLayout + Toolbar 整合 start -------------------------------
        toolbar.inflateMenu(R.menu.toolbar_menu);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        //同步状态
        drawerToggle.syncState();
        //侧边栏监听交给ActionBarDrawerToggle
        drawerLayout.addDrawerListener(drawerToggle);
        // ------------------------------ 课时2：DrawerLayout + Toolbar 整合 end -------------------------------

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_search) {
//                    startActivity(new Intent(MainActivity.this,SearchActivity.class));
                }
                return true;
            }
        });

        MenuItem downloadMenuItem = toolbar.getMenu().findItem(R.id.action_download);
        badgeActionProvider = (BadgeActionProvider) MenuItemCompat.getActionProvider(downloadMenuItem);
        badgeActionProvider.setIcon(DrawableCompat.wrap(new IconicsDrawable(this, HrgFont.Icon.cniao_download)
                .color(ContextCompat.getColor(this, R.color.white))));
        badgeActionProvider.setOnClickListener(v ->
                toAppManagerActivity(badgeActionProvider.getBadgeNum() > 0 ? 2 : 0)
        );

    }

    private void initTabLayout() {
        // -------------------- 课时3：TabLayout_ViewPager_Fragment可滑动的顶部菜单 start -----------------------
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), initFragment());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        // -------------------- 课时3：TabLayout_ViewPager_Fragment可滑动的顶部菜单 end -----------------------
    }

    private void initDrawerLayout() {
        // -----------------------课时1：DrawerLayout + NavigationView 实现侧滑菜单 start-------------------------
        // drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
        //     @Override
        //     public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
        //         Log.d("onDrawerSlide", "");
        //     }
        //
        //     @Override
        //     public void onDrawerOpened(@NonNull View drawerView) {
        //         Log.d("onDrawerOpened", "");
        //     }
        //
        //     @Override
        //     public void onDrawerClosed(@NonNull View drawerView) {
        //         Log.d("onDrawerClosed", "");
        //     }
        //
        //     @Override
        //     public void onDrawerStateChanged(int newState) {
        //         Log.d("onDrawerStateChanged", "");
        //     }
        // });
        headerView = navigationView.getHeaderView(0);
        ivUserHeadView = headerView.findViewById(R.id.img_avatar);
        ivUserHeadView.setImageDrawable(
                new IconicsDrawable(this,
                        HrgFont.Icon.cniao_head).colorRes(R.color.white));
        tvUsername = headerView.findViewById(R.id.txt_username);

        headerView.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        navigationView.getMenu().findItem(R.id.menu_app_update).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_loop));
        navigationView.getMenu().findItem(R.id.menu_download_manager).setIcon(new IconicsDrawable(this, HrgFont.Icon.cniao_download));
        navigationView.getMenu().findItem(R.id.menu_app_uninstall).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_trash_outline));
        navigationView.getMenu().findItem(R.id.menu_setting).setIcon(new IconicsDrawable(this, Ionicons.Icon.ion_ios_gear_outline));

        navigationView.getMenu().findItem(R.id.menu_logout).setIcon(new IconicsDrawable(this, HrgFont.Icon.cniao_shutdown));

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_app_update:
                    Toast.makeText(MainActivity.this, "menu_app_update", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_download_manager:
                    startActivity(new Intent(this, AppManagerActivity.class));
                    break;
                case R.id.menu_setting:
                    Toast.makeText(MainActivity.this, "menu_setting", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu_logout:
                    logout();// 退出登录
                    break;
            }
            return false;
        });
        // ----------------------- 课时1：DrawerLayout + NavigationView 实现侧滑菜单 end ------------------------
    }

    @Subscribe
    public void getUser(LoginBean.User user) {
        initUserHeadView(user);
    }

    private void initUserHeadView(LoginBean.User user) {
        Glide.with(this)
                .load(user.getLogo_url())
                .transform(new GlideCircleTransform(this))
                .into(ivUserHeadView);
        tvUsername.setText(user.getUsername());
        headerView.setClickable(false);
    }

    private void initUser() {
        Object object = ACache.get(MainActivity.this).getAsObject(Constant.USER);
        if (object != null) {
            LoginBean.User user = (LoginBean.User) object;
            initUserHeadView(user);
        } else {
            headerView.setFocusable(true);
        }
    }

    private void logout() {
        ACache aCache = ACache.get(this);
        aCache.put(Constant.TOKEN, "");
        aCache.put(Constant.USER, "");
        ivUserHeadView.setImageDrawable(
                new IconicsDrawable(this,
                        HrgFont.Icon.cniao_head).colorRes(R.color.white));
        tvUsername.setText("未登录");
        headerView.setClickable(true);
        Toast.makeText(this, "已退出登录", Toast.LENGTH_SHORT).show();
    }

    private void toAppManagerActivity(int position) {
        Intent intent = new Intent(MainActivity.this, AppManagerActivity.class);
        intent.putExtra(Constant.POSITION, position);
        startActivity(new Intent(intent));
    }

    @Override
    public void requestPermissonSuccess() {
        initToolbar();
        initDrawerLayout();
        initTabLayout();
        initUser();
    }

    @Override
    public void requestPermissonFail() {

    }

    @Override
    public void changeAppNeedUpdateCount(int count) {
        if (count > 0) {
            badgeActionProvider.setText(String.valueOf(count));
        } else {
            badgeActionProvider.hideBadge();
        }
    }
}

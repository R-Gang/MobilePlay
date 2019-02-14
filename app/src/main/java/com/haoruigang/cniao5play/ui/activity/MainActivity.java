package com.haoruigang.cniao5play.ui.activity;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.LoginBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.font.HrgFont;
import com.haoruigang.cniao5play.common.imageloader.GlideCircleTransform;
import com.haoruigang.cniao5play.common.util.ACache;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.ui.adapter.ViewPagerAdapter;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.mikepenz.iconics.IconicsDrawable;

import butterknife.BindView;

/**
 * 主页
 */
public class MainActivity extends BaseActivity {

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

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        initDrawerLayout();
        initTabLayout();
        initUser();
    }

    private void initTabLayout() {
        // -------------------- 课时3：TabLayout_ViewPager_Fragment可滑动的顶部菜单 start -----------------------
        PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
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
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_app_update:
                        Toast.makeText(MainActivity.this, "menu_app_update", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_download_manager:
                        Toast.makeText(MainActivity.this, "menu_message", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(MainActivity.this, "menu_setting", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_logout:
                        logout();// 退出登录
                        break;
                }
                return false;
            }
        });
        // ----------------------- 课时1：DrawerLayout + NavigationView 实现侧滑菜单 end ------------------------

        // ----------------------------- 课时2：DrawerLayout + Toolbar 整合 start -------------------------------
        toolbar.inflateMenu(R.menu.toolbar_menu);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        //同步状态
        drawerToggle.syncState();
        //侧边栏监听交给ActionBarDrawerToggle
        drawerLayout.addDrawerListener(drawerToggle);
        // ------------------------------ 课时2：DrawerLayout + Toolbar 整合 end -------------------------------
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

}

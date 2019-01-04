package com.haoruigang.cniao5play.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.ui.adapter.ViewPagerAdapter;

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
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "headerView", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, IconicsActivity.class));
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_app_update:
                        Toast.makeText(MainActivity.this, "menu_app_update", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_message:
                        Toast.makeText(MainActivity.this, "menu_message", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_setting:
                        Toast.makeText(MainActivity.this, "menu_setting", Toast.LENGTH_SHORT).show();
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
}

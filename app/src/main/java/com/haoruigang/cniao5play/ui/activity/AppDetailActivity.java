package com.haoruigang.cniao5play.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.imageloader.ImageLoader;
import com.haoruigang.cniao5play.common.util.DensityUtil;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.ui.fragment.AppDetailFragment;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.BindView;

/**
 * app详情
 */
public class AppDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_temp)
    View viewTemp;
    @BindView(R.id.view_coordinator)
    CoordinatorLayout viewCoordinator;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.view_content)
    FrameLayout mViewContent;

    private AppInfoBean appInfo;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {

        appInfo = (AppInfoBean) getIntent().getSerializableExtra("appInfo");

        ImageLoader.load(ApiService.BASE_IMG_URL + appInfo.getIcon(), imgIcon);
        txtName.setText(appInfo.getDisplayName());

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(
                new IconicsDrawable(this)
                        .icon(Ionicons.Icon.ion_ios_arrow_back)
                        .sizeDp(16)
                        .color(getResources().getColor(R.color.md_white_1000)
                        )
        );
        toolbar.setNavigationOnClickListener(v -> finish());

        View view = mApplication.getView();
        Bitmap bitmap = getViewImageCache(view);
        if (bitmap != null) {
//            viewTemp.setImageBitmap(bitmap);
            viewTemp.setBackground(new BitmapDrawable(bitmap));
        }
        // 固定到相对位置
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(viewTemp.getLayoutParams());
        marginParams.leftMargin = left;
        marginParams.topMargin = top - DensityUtil.getStatusBarH(this);//减去状态栏的高度
        marginParams.width = view.getWidth();
        marginParams.height = view.getHeight();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(marginParams);
        viewTemp.setLayoutParams(params);

        Expand();

    }

    // 缓存显示view
    private Bitmap getViewImageCache(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        if (bitmap == null) {
            return null;
        }

        bitmap = Bitmap.createBitmap(bitmap);
        view.destroyDrawingCache();

        return bitmap;
    }

    // 向上下展开
    public void Expand() {
        int h = DensityUtil.getScreenH(this);
        ObjectAnimator animator = ObjectAnimator.ofFloat(viewTemp, "scaleY",
                1f, (float) h);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                // 展开动画开始改变背景颜色
                viewTemp.setBackgroundColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 显示App详情
                viewTemp.setVisibility(View.GONE);
                viewCoordinator.setVisibility(View.VISIBLE);
                initFragment();
            }
        });
        animator.setStartDelay(300);// 延迟动画启动
        animator.setDuration(2000);// 动画持续时间
        animator.start();
    }

    private void initFragment() {
        AppDetailFragment appDetail = new AppDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", appInfo.getId());
        appDetail.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.view_content, appDetail)
                .commitAllowingStateLoss();
    }

}

package com.haoruigang.cniao5play.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.common.util.DensityUtil;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.presenter.AppDetailPresenter;

import butterknife.BindView;

/**
 * app详情
 */
public class AppDetailActivity extends BaseActivity<AppDetailPresenter> {

    @BindView(R.id.view_content)
    FrameLayout mViewContent;

    @Override
    public int setLayout() {
        return R.layout.activity_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        View view = mApplication.getView();
        Bitmap bitmap = getViewImageCache(view);
        if (bitmap != null) {
//            mViewContent.setImageBitmap(bitmap);
            mViewContent.setBackground(new BitmapDrawable(bitmap));
        }
        // 固定到相对位置
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(mViewContent.getLayoutParams());
        marginParams.leftMargin = left;
        marginParams.topMargin = top - DensityUtil.getStatusBarH(this);//减去状态栏的高度
        marginParams.width = view.getWidth();
        marginParams.height = view.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginParams);
        mViewContent.setLayoutParams(params);

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
        ObjectAnimator animator = ObjectAnimator.ofFloat(mViewContent, "scaleY",
                1f, (float) h);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                // 展开动画开始改变背景颜色
                mViewContent.setBackgroundColor(getResources().getColor(R.color.white));
            }
        });
        animator.setStartDelay(600);// 延迟动画启动
        animator.setDuration(10000);// 动画持续时间
        animator.start();
    }

}

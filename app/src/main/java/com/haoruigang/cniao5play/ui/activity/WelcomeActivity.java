package com.haoruigang.cniao5play.ui.activity;

import android.content.Intent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.eftimoff.androipathview.PathView;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.di.component.AppComponent;

import butterknife.BindView;

/**
 * 欢迎页
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.pathView)
    PathView pathView;
    @BindView(R.id.iv_mobile)
    ImageView ivMobile;

    @Override
    public int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        pathView.getPathAnimator()
                .delay(100)//停留延迟
                .duration(5000)//持续时间
                .interpolator(new AccelerateDecelerateInterpolator())
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        ivMobile.setVisibility(View.VISIBLE);
                        jump();
                    }
                })
                .start();
    }

    //跳转方法
    private void jump() {
        startActivity(new Intent(this, GuideActivity.class));
        finish();
    }

}

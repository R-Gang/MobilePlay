package com.haoruigang.cniao5play.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 课时4：在Android上使用SVG矢（读音:[shǐ]）量图
 * 课时5：在Android中使用iconfont图标
 * 课时6：自定义iconfont
 */
public class IconicsActivity extends BaseActivity {

    @BindView(R.id.iv_telephone)
    ImageView ivTelephone;

    @Override
    public int setLayout() {
        return R.layout.activity_iconics;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    public void init() {
        Drawable drawable = new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_telephone_outline)
                .color(Color.RED)
                .sizeDp(50);
        ivTelephone.setImageDrawable(drawable);
    }
}

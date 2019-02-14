package com.haoruigang.cniao5play.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.typeicons_typeface_library.Typeicons;

import butterknife.BindView;

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
                .icon((IIcon) Iconics.findFont(Typeicons.Icon.typ_phone_outline))
                .color(Color.RED)
                .sizeDp(50);
        ivTelephone.setImageDrawable(drawable);
    }
}

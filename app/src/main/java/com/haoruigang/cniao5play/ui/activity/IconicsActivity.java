package com.haoruigang.cniao5play.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.haoruigang.cniao5play.R;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.context.IconicsLayoutInflater2;
import com.mikepenz.ionicons_typeface_library.Ionicons;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 课时6：自定义iconfont
 */
public class IconicsActivity extends AppCompatActivity {

    @BindView(R.id.iv_telephone)
    ImageView ivTelephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iconics);
        ButterKnife.bind(this);
        Drawable drawable = new IconicsDrawable(this)
                .icon(Ionicons.Icon.ion_ios_telephone_outline)
                .color(Color.RED)
                .sizeDp(45);
        ivTelephone.setImageDrawable(drawable);

    }
}

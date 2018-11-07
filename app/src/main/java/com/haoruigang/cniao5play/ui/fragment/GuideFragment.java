package com.haoruigang.cniao5play.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.di.component.AppComponent;

import butterknife.BindView;

public class GuideFragment extends BaseFragment {

    @BindView(R.id.img_icon)
    ImageView imgIcon;

    private static final String IMGRES = "IMGRESID";

    public static GuideFragment newInstance(int imgResId) {
        GuideFragment fragment = new GuideFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(IMGRES, imgResId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int setLayout() {
        return R.layout.fragment_guide;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }


    @Override
    public void init() {
        Bundle args = getArguments();
        if (args != null) {
            int bgResId = args.getInt(IMGRES);
            imgIcon.setImageResource(bgResId);
        }
    }

}

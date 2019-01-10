package com.haoruigang.cniao5play.ui.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.common.util.DensityUtil;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.presenter.AppDetailPresenter;
import com.hwangjr.rxbus.annotation.Subscribe;

import butterknife.BindView;

/**
 * app详情
 */
public class AppDetailActivity extends BaseActivity {

    @BindView(R.id.imgView)
    ImageView imgView;

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
            imgView.setImageBitmap(bitmap);
        }
        // 固定到相对位置
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        ViewGroup.MarginLayoutParams marginParams = new ViewGroup.MarginLayoutParams(imgView.getLayoutParams());
        marginParams.leftMargin = left;
        marginParams.topMargin = top - DensityUtil.getStatusBarH(this);//减去状态栏的高度
        marginParams.width = view.getWidth();
        marginParams.height = view.getHeight();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(marginParams);
        imgView.setLayoutParams(params);
    }

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

}

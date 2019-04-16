package com.haoruigang.cniao5play.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.imageloader.ImageLoader;
import com.haoruigang.cniao5play.common.util.DateUtils;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.di.component.AppComponent;
import com.haoruigang.cniao5play.di.component.DaggerAppDetailComponent;
import com.haoruigang.cniao5play.di.module.AppDetailModule;
import com.haoruigang.cniao5play.presenter.AppDetailPresenter;
import com.haoruigang.cniao5play.presenter.contract.AppDetailContract;
import com.haoruigang.cniao5play.ui.adapter.AppInfoAdapter;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * App详情Fragment
 */
public class AppDetailFragment extends ProgressFragment<AppDetailPresenter> implements AppDetailContract.AppDetailView {

    @BindView(R.id.view_gallery)
    LinearLayout viewGallery;
    @BindView(R.id.expandable_text)
    TextView expandableText;
    @BindView(R.id.expand_collapse)
    ImageButton expandCollapse;
    @BindView(R.id.view_introduction)
    ExpandableTextView viewIntroduction;
    @BindView(R.id.txt_update_time)
    TextView txtUpdateTime;
    @BindView(R.id.txt_version)
    TextView txtVersion;
    @BindView(R.id.txt_apk_size)
    TextView txtApkSize;
    @BindView(R.id.txt_publisher)
    TextView txtPublisher;
    @BindView(R.id.txt_publisher2)
    TextView txtPublisher2;
    @BindView(R.id.recycler_view_same_dev)
    RecyclerView recyclerViewSameDev;
    @BindView(R.id.recycler_view_relate)
    RecyclerView recyclerViewRelate;

    private AppInfoAdapter mAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_app_detail;
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerAppDetailComponent.builder().appComponent(appComponent)
                .appDetailModule(new AppDetailModule(this))
                .build().inject(this);
    }

    @Override
    public void init() {

        int id = getArguments().getInt("id");
        mPresenter.getAppDetail(id);

    }

    @Override
    public void showDetail(AppInfoBean appInfo) {
        showScreenshot(appInfo.getScreenshot());

        viewIntroduction.setText(appInfo.getIntroduction());

        txtUpdateTime.setText(DateUtils.formatDate(appInfo.getUpdateTime()));
        txtApkSize.setText(String.format("%s\tMb", appInfo.getApkSize() / 1014 / 1024));
        txtVersion.setText(appInfo.getVersionName());
        txtPublisher.setText(appInfo.getPublisherName());
        txtPublisher2.setText(appInfo.getPublisherName());

        recyclerViewSameDev.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = AppInfoAdapter
                .builder().layout(R.layout.template_appinfo2).build();
        mAdapter.addData(appInfo.getSameDevAppInfoList());
        recyclerViewSameDev.setAdapter(mAdapter);

        recyclerViewRelate.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = AppInfoAdapter
                .builder().layout(R.layout.template_appinfo2).build();
        mAdapter.addData(appInfo.getRelateAppInfoList());
        recyclerViewRelate.setAdapter(mAdapter);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }

    private void showScreenshot(String screenShot) {
        List<String> urls = Arrays.asList(screenShot.split(","));
        for (String url : urls) {
            ImageView imageView = (ImageView) LayoutInflater.from(getActivity())
                    .inflate(R.layout.template_imageview, viewGallery, false);
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(540, 720);
//            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
//                    DensityUtil.getScreenW(getActivity()) / 2,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(layoutParams);
            ImageLoader.load(Constant.BASE_IMG_URL + url, imageView);
            viewGallery.addView(imageView);
        }
    }

}

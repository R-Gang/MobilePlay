package com.haoruigang.cniao5play.ui.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.Constant;
import com.haoruigang.cniao5play.common.imageloader.ImageLoader;
import com.haoruigang.cniao5play.ui.widget.downloadbutton.DownloadButtonConntroller;
import com.haoruigang.cniao5play.ui.widget.downloadbutton.DownloadProgressButton;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import zlc.season.rxdownload2.RxDownload;

/**
 * 推荐热门应用游戏
 */
public class AppInfoAdapter extends BaseQuickAdapter<AppInfoBean, BaseViewHolder> {

    private Builder builder;

    private DownloadButtonConntroller mDownloadButtonConntroller;

    private AppInfoAdapter(Builder builder) {
        super(builder.layoutId);
        this.builder = builder;
        mDownloadButtonConntroller = new DownloadButtonConntroller(builder.mRxDownload);
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfoBean appInfo) {
        // 强行关闭复用
        helper.setIsRecyclable(false);
        final ImageView imgIcon = helper.itemView.findViewById(R.id.img_icon);
        ImageLoader.load(Constant.BASE_IMG_URL + appInfo.getIcon(), imgIcon);

        final TextView tvAppName = helper.itemView.findViewById(R.id.tv_app_name);
        tvAppName.setText(appInfo.getDisplayName());

        final TextView tvBrief = helper.itemView.findViewById(R.id.tv_brief);
        final DownloadProgressButton btnDl = helper.itemView.findViewById(R.id.btn_download_progress);

        if (builder.isUpdateStatus) {

            ExpandableTextView viewChangeLog = helper.getView(R.id.view_changelog);
            viewChangeLog.setText(appInfo.getChangeLog());

            if (tvBrief != null) {
                tvBrief.setVisibility(View.VISIBLE);
                tvBrief.setText("v" + appInfo.getVersionName() + "  " + (appInfo.getApkSize() / 1014 / 1024) + "Mb");
            }

            btnDl.setText("升级");

        } else {

            if (tvBrief != null) {
                tvBrief.setVisibility(builder.isShowBrief ? View.VISIBLE : View.GONE);
                tvBrief.setText(appInfo.getPublisherName());
            }

            TextView tvCatenory = helper.itemView.findViewById(R.id.tv_catenory);
            if (tvCatenory != null) {
                tvCatenory.setVisibility(builder.isShowCategoryName ? View.VISIBLE : View.GONE);
                tvCatenory.setText(appInfo.getLevel1CategoryName());
            }

            TextView tvPosition = helper.itemView.findViewById(R.id.tv_position);
            if (tvPosition != null) {
                tvPosition.setVisibility(builder.isShowPosition ? View.VISIBLE : View.GONE);
                tvPosition.setText(String.format("%s.", appInfo.getPosition() + 1));
            }

            TextView txtApkSize = helper.itemView.findViewById(R.id.txt_apk_size);
            if (txtApkSize != null) {
                txtApkSize.setText(String.format("%s\tMb", appInfo.getApkSize() / 1014 / 1024));
            }

            if (btnDl != null) {
                helper.addOnClickListener(btnDl.getId());
                btnDl.setVisibility(View.VISIBLE);
                mDownloadButtonConntroller.handClick(btnDl, appInfo);
            }

            final Button btnDownload = helper.itemView.findViewById(R.id.btn_download);
            if (btnDownload != null) {
                btnDownload.setVisibility(View.VISIBLE);
            }
        }
    }

    public static class Builder {
        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;
        private boolean isUpdateStatus;

        private RxDownload mRxDownload;

        private int layoutId = R.layout.template_appinfo;

        public Builder showPosition(boolean b) {
            isShowPosition = b;
            return this;
        }

        public Builder showCategoryName(boolean b) {
            isShowCategoryName = b;
            return this;
        }

        public Builder showBrief(boolean b) {
            isShowBrief = b;
            return this;
        }

        public AppInfoAdapter build() {
            return new AppInfoAdapter(this);
        }

        public Builder layout(int resId) {
            this.layoutId = resId;
            return this;
        }

        public Builder rxDownload(RxDownload rxDownload) {
            this.mRxDownload = rxDownload;
            return this;
        }

        public Builder updateStatus(boolean b) {
            this.isUpdateStatus = b;
            return this;
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}

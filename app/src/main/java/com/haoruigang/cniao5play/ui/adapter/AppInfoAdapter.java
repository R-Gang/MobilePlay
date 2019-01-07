package com.haoruigang.cniao5play.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.common.imageloader.ImageLoader;
import com.haoruigang.cniao5play.data.http.ApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 推荐热门应用游戏
 */
public class AppInfoAdapter extends BaseQuickAdapter<AppInfoBean, AppInfoAdapter.ViewHolder> {

    private Builder builder;

    public AppInfoAdapter(Builder builder) {
        super(R.layout.template_appinfo2);
        this.builder = builder;
        openLoadAnimation();
    }

    @Override
    protected void convert(ViewHolder helper, AppInfoBean appInfo) {
        ImageLoader.load(ApiService.BASE_IMG_URL + appInfo.getIcon(), helper.imgIcon);
        helper.tvAppName.setText(appInfo.getDisplayName());

        helper.tvCatenory.setVisibility(builder.isShowCategoryName ? View.VISIBLE : View.GONE);
        helper.tvCatenory.setText(appInfo.getLevel1CategoryName());

        helper.tvBrief.setVisibility(builder.isShowBrief ? View.VISIBLE : View.GONE);
        helper.tvBrief.setText(appInfo.getPublisherName());

        helper.tvPosition.setVisibility(builder.isShowPosition ? View.VISIBLE : View.GONE);
        helper.tvPosition.setText(String.format("%s.", appInfo.getPosition() + 1));
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.tv_app_name)
        TextView tvAppName;
        @BindView(R.id.tv_catenory)
        TextView tvCatenory;
        @BindView(R.id.tv_brief)
        TextView tvBrief;
        @BindView(R.id.iv_dl)
        ImageView ivDl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class Builder {
        private boolean isShowPosition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;

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

    }

    public static Builder builder() {
        return new Builder();
    }

}

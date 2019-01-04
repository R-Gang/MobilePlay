package com.haoruigang.cniao5play.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.common.imageloader.ImageLoader;
import com.haoruigang.cniao5play.data.http.ApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo, AppInfoAdapter.ViewHolder> {
    //@Nullable List<AppInfo> data
    AppInfoAdapter() {
        super(R.layout.template_appinfo2);
    }

    @Override
    protected void convert(ViewHolder helper, AppInfo appInfo) {
        ImageLoader.load(ApiService.BASE_IMG_URL + appInfo.getIcon(), helper.imgIcon);
        helper.tvAppName.setText(appInfo.getDisplayName());
        helper.tvBrief.setText(appInfo.getBriefShow());
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.tv_app_name)
        TextView tvAppName;
        @BindView(R.id.tv_brief)
        TextView tvBrief;
        @BindView(R.id.iv_dl)
        ImageView ivDl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

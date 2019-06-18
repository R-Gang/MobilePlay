package com.haoruigang.cniao5play.ui.adapter;

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

import zlc.season.rxdownload2.RxDownload;
import zlc.season.rxdownload2.entity.DownloadRecord;

/**
 * 下载 推荐热门应用游戏
 */
public class DownloadingAdapter extends BaseQuickAdapter<DownloadRecord, BaseViewHolder> {

    public DownloadButtonConntroller mDownloadButtonConntroller;

    public DownloadingAdapter(RxDownload rxDownload) {
        super(R.layout.template_appinfo);
        mDownloadButtonConntroller = new DownloadButtonConntroller(rxDownload);
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder helper, DownloadRecord item) {
        // 强行关闭复用
        helper.setIsRecyclable(false);
        AppInfoBean appInfo = mDownloadButtonConntroller.downloadRecord2AppInfo(item);

        ImageView imgIcon = helper.itemView.findViewById(R.id.img_icon);
        ImageLoader.load(Constant.BASE_IMG_URL + appInfo.getIcon(), imgIcon);

        TextView tvAppName = helper.itemView.findViewById(R.id.tv_app_name);
        tvAppName.setText(appInfo.getDisplayName());

        TextView tvCatenory = helper.itemView.findViewById(R.id.tv_catenory);
        if (tvCatenory != null) {
            tvCatenory.setText(appInfo.getLevel1CategoryName());
        }

        final DownloadProgressButton btnDl = helper.itemView.findViewById(R.id.btn_download_progress);
        if (btnDl != null) {
            helper.addOnClickListener(btnDl.getId());
            mDownloadButtonConntroller.handClick(btnDl, item);
        }
    }

}

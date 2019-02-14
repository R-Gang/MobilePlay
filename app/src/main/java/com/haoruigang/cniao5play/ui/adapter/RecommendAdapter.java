package com.haoruigang.cniao5play.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 课时7：RecycleView 的简单使用
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private Context mContext;

    List<AppInfoBean> datas = new ArrayList<>();

    public RecommendAdapter(Context mContext, List<AppInfoBean> datas) {
        this.datas.addAll(datas);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.template_appinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppInfoBean appInfo = datas.get(position);
        Picasso.with(mContext).load(ApiService.BASE_IMG_URL + appInfo.getIcon()).into(holder.imgIcon);
        holder.tvAppName.setText(appInfo.getDisplayName());
        holder.tvBrief.setText(String.format("%sMB", appInfo.getApkSize() / 1024 / 1024));
    }

    public void setData(List<AppInfoBean> datas) {
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.tv_app_name)
        TextView tvAppName;
        @BindView(R.id.tv_brief)
        TextView tvBrief;
        @BindView(R.id.iv_dl)
        ImageView ivDl;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

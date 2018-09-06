package com.haoruigang.cniao5play.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.http.ApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private Context mContext;

    List<AppInfo> datas = new ArrayList<>();

    public RecommendAdapter(Context mContext, List<AppInfo> datas) {
        this.datas.addAll(datas);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.template_recommend_app, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppInfo appInfo = datas.get(position);
        Picasso.with(mContext).load(ApiService.BASE_IMG_URL + appInfo.getIcon()).into(holder.imgIcon);
        holder.textTitle.setText(appInfo.getDisplayName());
        holder.textSize.setText(String.format("%sMB", appInfo.getApkSize() / 1024 / 1024));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.text_title)
        TextView textTitle;
        @BindView(R.id.text_size)
        TextView textSize;
        @BindView(R.id.btn_dl)
        Button btnDl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

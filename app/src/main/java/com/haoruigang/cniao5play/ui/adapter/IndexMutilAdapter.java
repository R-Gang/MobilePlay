package com.haoruigang.cniao5play.ui.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.ui.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexMutilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APPS = 3;
    private static final int TYPE_GAMES = 4;

    private LayoutInflater inflater;

    private IndexBean mIndexBean;

    public IndexMutilAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(inflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {
            return new NavIconViewHolder(inflater.inflate(R.layout.template_nav_icon, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<IndexBean.Banner> banners = mIndexBean.getBanners();
            List<String> urls = new ArrayList<>(banners.size());
            for (IndexBean.Banner banner : banners) {
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.mBanner.setViewUrls(urls);
        } else if (holder instanceof NavIconViewHolder) {

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        } else {
            return position;
        }
    }

    public void setData(IndexBean mIndexBean) {
        this.mIndexBean = mIndexBean;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        BannerLayout mBanner;

        BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NavIconViewHolder extends RecyclerView.ViewHolder {

        public NavIconViewHolder(View itemView) {
            super(itemView);
        }
    }

    class AppsViewHolder extends RecyclerView.ViewHolder {

        public AppsViewHolder(View itemView) {
            super(itemView);
        }
    }

    class GamesViewHolder extends RecyclerView.ViewHolder {

        public GamesViewHolder(View itemView) {
            super(itemView);
        }
    }

}

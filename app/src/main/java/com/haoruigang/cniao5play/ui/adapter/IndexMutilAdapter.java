package com.haoruigang.cniao5play.ui.adapter;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.common.imageloader.ImageLoader;
import com.haoruigang.cniao5play.ui.widget.BannerLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IndexMutilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private static final int TYPE_BANNER = 1;
    private static final int TYPE_ICON = 2;
    private static final int TYPE_APPS = 3;
    private static final int TYPE_GAMES = 4;

    private LayoutInflater inflater;
    private Context mContext;
    private IndexBean mIndexBean;

    public IndexMutilAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(inflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {
            return new NavIconViewHolder(inflater.inflate(R.layout.template_nav_icon, parent, false));
        } else if (viewType == TYPE_APPS) {
            return new AppGamesViewHolder(inflater.inflate(R.layout.template_recyclerview_with_title, null, false), TYPE_APPS);
        } else if (viewType == TYPE_GAMES) {
            return new AppGamesViewHolder(inflater.inflate(R.layout.template_recyclerview_with_title, null, false), TYPE_GAMES);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) { // or position ==0
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            List<IndexBean.Banner> banners = mIndexBean.getBanners();
            List<String> urls = new ArrayList<>(banners.size());
            for (IndexBean.Banner banner : banners) {
                urls.add(banner.getThumbnail());
            }
            bannerViewHolder.mBanner.setViewUrls(urls);
            bannerViewHolder.mBanner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(mContext, position, Toast.LENGTH_SHORT).show();
                }
            });
        } else if (holder instanceof NavIconViewHolder) {// or position ==1
            NavIconViewHolder navIconViewHolder = (NavIconViewHolder) holder;
            navIconViewHolder.layoutHotApp.setOnClickListener(this);
            navIconViewHolder.layoutHotGame.setOnClickListener(this);
            navIconViewHolder.layoutHotSubject.setOnClickListener(this);
        } else {
            AppGamesViewHolder viewHolder = (AppGamesViewHolder) holder;
            AppInfoAdapter appInfoAdapter = new AppInfoAdapter();
            if (viewHolder.viewType == TYPE_APPS) {
                viewHolder.text.setText("热门应用");
                appInfoAdapter.addData(mIndexBean.getRecommendApps());
            } else {
                viewHolder.text.setText("热门游戏");
                appInfoAdapter.addData(mIndexBean.getRecommendGames());
            }
            viewHolder.recyclerView.setAdapter(appInfoAdapter);
            viewHolder.recyclerView.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
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

    @Override
    public void onClick(View v) {

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        BannerLayout mBanner;

        BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mBanner.setImageLoader(new ImgLoader());
        }
    }

    class NavIconViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_hot_app)
        LinearLayout layoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout layoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout layoutHotSubject;

        NavIconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ImgLoader implements BannerLayout.ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoader.load(path, imageView);
        }
    }

    class AppGamesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.recycler_view)
        RecyclerView recyclerView;

        int viewType;

        AppGamesViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.viewType = viewType;
            initRecyclerView(recyclerView);
        }
    }

    private void initRecyclerView(RecyclerView recyclerView) {
        //为RecyClerView设置布局管理器
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext) {
            @Override
            public boolean canScrollVertically() {
                return false;//不允许垂直滚动
            }
        });
        //为RecyClerView设置分割线(这个DividerItemDecoration可以自定义)
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL));
        //动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}

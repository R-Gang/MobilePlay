package com.haoruigang.cniao5play.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haoruigang.cniao5play.R;
import com.haoruigang.cniao5play.bean.CategoryBean;
import com.haoruigang.cniao5play.common.imageloader.ImageLoader;
import com.haoruigang.cniao5play.data.http.ApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 分类
 */
public class CategoryAdapter extends BaseQuickAdapter<CategoryBean, CategoryAdapter.ViewHolder> {


    public CategoryAdapter() {
        super(R.layout.template_category);
        openLoadAnimation();
    }

    @Override
    protected void convert(ViewHolder helper, CategoryBean categoryBean) {
        ImageLoader.load(ApiService.BASE_IMG_URL + categoryBean.getIcon(), helper.imgIcon);
        helper.textName.setText(categoryBean.getName());
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.text_name)
        TextView textName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

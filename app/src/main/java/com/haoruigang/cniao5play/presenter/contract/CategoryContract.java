package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.CategoryBean;
import com.haoruigang.cniao5play.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;

public interface CategoryContract {

    interface ICategoryModel {

        Observable<BaseBean<List<CategoryBean>>> category();

    }

    interface CategoryView extends BaseView {

        void showData(List<CategoryBean> categoryBean);

    }

}

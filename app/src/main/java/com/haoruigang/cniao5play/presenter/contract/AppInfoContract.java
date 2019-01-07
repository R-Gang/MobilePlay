package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.ui.BaseView;

public interface AppInfoContract {

    interface View extends BaseView {

        void showResult(IndexBean indexBean);

        void onRequestPermissionSuccess();

        void onRequestPermissionFail();

    }

    interface AppInfoView extends BaseView {

        void showResult(PageBean<AppInfoBean> appInfoBean);

        void onLoadMoreComplete();

    }

}

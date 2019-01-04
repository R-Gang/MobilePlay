package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.presenter.BasePresenter;
import com.haoruigang.cniao5play.ui.BaseView;

import java.util.List;

public interface AppInfoContract {

    interface View extends BaseView {

        void showResult(IndexBean indexBean);

        void onRequestPermissionSuccess();

        void onRequestPermissionFail();

    }

    interface TopListView extends BaseView {

        void showResult(PageBean<AppInfo> appInfoBean);

        void onLoadMoreComplete();

    }

}

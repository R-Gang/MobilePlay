package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.ui.BaseView;

import io.reactivex.Observable;

public interface AppDetailContract {

    interface IAppDetailModule {

        Observable<BaseBean<AppInfoBean>> getAppDetail(int id);

    }

    interface AppDetailView extends BaseView {

        void showDetail(AppInfoBean appInfo);

    }

}

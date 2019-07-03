package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.requestbean.AppsUpdateBean;
import com.haoruigang.cniao5play.ui.BaseView;

import java.util.List;

import io.reactivex.Observable;

public interface MainContract {

    public interface MainView extends BaseView {

        void requestPermissonSuccess();

        void requestPermissonFail();

        void changeAppNeedUpdateCount(int count);

    }


    public interface IMainModel {

        Observable<BaseBean<List<AppInfoBean>>> getUpdateApps(AppsUpdateBean param);

    }
}

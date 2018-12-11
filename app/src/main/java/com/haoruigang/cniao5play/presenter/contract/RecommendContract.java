package com.haoruigang.cniao5play.presenter.contract;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.presenter.BasePresenter;
import com.haoruigang.cniao5play.ui.BaseView;

import java.util.List;

public interface RecommendContract {

    interface View extends BaseView {

        void showResult(List<AppInfo> datas);

        void showNoData();

        void showError(String msg);

        void onRequestPermissionSuccess();

        void onRequestPermissionFail();

    }

}

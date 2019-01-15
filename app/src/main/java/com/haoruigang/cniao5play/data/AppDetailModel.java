package com.haoruigang.cniao5play.data;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.data.http.ApiService;
import com.haoruigang.cniao5play.presenter.contract.AppDetailContract;

import io.reactivex.Observable;

public class AppDetailModel implements AppDetailContract.IAppDetailModule {

    private ApiService apiService;

    public AppDetailModel(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<BaseBean<AppInfoBean>> getAppDetail(int id) {
        return apiService.getAppDetail(id);
    }
}

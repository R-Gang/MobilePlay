package com.haoruigang.cniao5play.data;

import com.haoruigang.cniao5play.bean.AppInfoBean;
import com.haoruigang.cniao5play.bean.BaseBean;
import com.haoruigang.cniao5play.bean.IndexBean;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.data.http.ApiService;

import io.reactivex.Observable;

public class AppInfoModel {

    private ApiService apiService;

    public AppInfoModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<BaseBean<PageBean<AppInfoBean>>> getApps() {
        return apiService.getApps("{\"page\":0}");
    }

    public Observable<BaseBean<IndexBean>> index() {
        return apiService.index();
    }

    public Observable<BaseBean<PageBean<AppInfoBean>>> topList(int page) {
        return apiService.topList(page);
    }

    public Observable<BaseBean<PageBean<AppInfoBean>>> games(int page) {
        return apiService.games(page);
    }

}

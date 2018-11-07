package com.haoruigang.cniao5play.data;

import com.haoruigang.cniao5play.bean.AppInfo;
import com.haoruigang.cniao5play.bean.PageBean;
import com.haoruigang.cniao5play.data.http.ApiService;

import io.reactivex.Observable;
import retrofit2.Callback;

public class RecommendModel {

    private ApiService apiService;

    public RecommendModel(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<PageBean<AppInfo>> getApps() {
        return apiService.getApps("{“page”:0}");
    }

}
